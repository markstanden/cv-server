package dev.markstanden.datastore

import dev.markstanden.environment.getGithubVariables
import dev.markstanden.models.CV
import dev.markstanden.models.GitHubAPI
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class GitHub : DataStore {
	companion object {
		const val GITHUB_JSON = "application/vnd.github.v3+json"
		const val CV_FILENAME = "cv.json"
		const val BASE_DIRECTORY_NAME = "tech"
		const val COVER_LETTER_FILENAME = "coverletter.json"
		private val json = Json { ignoreUnknownKeys = true }
		private val env = getGithubVariables()
	}

	private fun urlGenerator(userName: String) =
		{ repoName: String ->
			{ id: String ->
				{ filename: String ->
					{ branch: String ->
						"https://api.github.com/repos/$userName/$repoName/contents/$id/$filename?ref=$branch"
					}
				}
			}
		}


	override suspend fun getCV(branch: String): Pair<CV?, HttpStatusCode> {

		val fileContents = getFile(branch = branch, fileName = CV_FILENAME)
		// The raw file downloaded, parse to a CV object
		val cv = json.decodeFromString<CV>(fileContents.body())

		return Pair(cv, HttpStatusCode.OK)
	}


	private suspend fun getFile(branch: String, fileName: String): HttpResponse {
		val client = HttpClient(CIO)
		val getWithAuthorization = get(client)(env.personalAccessToken)
		val url = urlGenerator(env.userName)(env.repoName)(BASE_DIRECTORY_NAME)(fileName)(branch)
		println(url)
		val lookupResponse = getWithAuthorization(url)
		println(lookupResponse.body() as String)
		// Abort early if the file is not found or inaccessible
		if (lookupResponse.status != HttpStatusCode.OK) {
			client.close()
			return lookupResponse
		}

		// GH response for a file lookup contains the file SHA and a direct download link.
		val fileInfo = json.decodeFromString<GitHubAPI.Contents>(lookupResponse.body())

		return getWithAuthorization(fileInfo.download_url)
	}


	override suspend fun getCover(branch: String): Pair<String, HttpStatusCode> {
		val fileContents = getFile(branch = branch, fileName = COVER_LETTER_FILENAME)
		// The raw file downloaded, parse to a CV object
		return Pair(fileContents.body(), HttpStatusCode.OK)
	}

	private fun get(client: HttpClient) =
		{ personalAccessToken: String ->
			{ url: String ->
				runBlocking {
					client.get(url) {
						headers["Accept"] = GITHUB_JSON
						headers["Authorization"] = "token $personalAccessToken"
					}
				}
			}
		}
}
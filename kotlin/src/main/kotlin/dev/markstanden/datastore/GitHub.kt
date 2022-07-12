package dev.markstanden.datastore

import dev.markstanden.environment.getGithubVariables
import dev.markstanden.models.CV
import dev.markstanden.models.GitHubAPI
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class GitHub : DataStore {
	companion object {
		const val GITHUB_JSON = "application/vnd.github.v3+json"
		private val json = Json { ignoreUnknownKeys = true }
		private val env = getGithubVariables()
	}

	private fun cvUrl(id: String) =
		"https://api.github.com/repos/${env.userName}/${env.repoName}/contents/$id/cv.json"

	override suspend fun getCV(id: String): Pair<CV?, HttpStatusCode> {

		val client = HttpClient(CIO)
		val getWithAuthorization = get(client)(env.personalAccessToken)

		val res = getWithAuthorization(cvUrl(id))

		// Abort early if the file is not found
		if (res.status != HttpStatusCode.OK) {
			client.close()
			return Pair(null, res.status)
		}

		// GH response for a file lookup contains the file SHA a direct download link.
		val fileInfo = json.decodeFromString<GitHubAPI.Contents>(res.body())

		val fileContents = getWithAuthorization(fileInfo.download_url)

		// The raw file downloaded, parse to a CV object
		val cv = json.decodeFromString<CV>(fileContents.body())

		return Pair(cv, HttpStatusCode.OK)
	}

	override suspend fun getCover(id: String): Pair<String, HttpStatusCode> {
		return Pair("", HttpStatusCode.OK)
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
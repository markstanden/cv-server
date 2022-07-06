package dev.markstanden.routes

import dev.markstanden.environment.getGithubVariables
import dev.markstanden.models.CV
import dev.markstanden.models.GitHubAPI
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

const val GITHUB_JSON = "application/vnd.github.v3+json"
private val json = Json { ignoreUnknownKeys = true }
private val env = getGithubVariables()


// TODO: 02/07/2022 refactor this route with github specific code extracted.
fun Route.cvRoute() {
	route("/cv/{folder}") {
		get {
			// TODO: 06/07/2022 clean/validate user input
			val folder = call.parameters["folder"]

			val url = "https://api.github.com/repos/${env.userName}/${env.repoName}/contents/$folder/cv.json"

			val client = HttpClient(CIO)

			val res = client.get(url) {
				headers["Accept"] = GITHUB_JSON
				headers["Authorization"] = "token ${env.personalAccessToken}"
			}

			call.response.status(res.status)

			// TODO: 02/07/2022 Use error specific, styled pages and redirects
			// Abort early if the file is not found
			if (res.status != HttpStatusCode.OK) {
				client.close()
				call.respond("Something went wrong...")
				return@get
			}

			// GH response for a file lookup contains the file SHA a direct download link.
			val fileInfo = json.decodeFromString<GitHubAPI.Contents>(res.body())

			val fileContents = client.get(fileInfo.download_url) {
				headers["Accept"] = GITHUB_JSON
				headers["Authorization"] = "token ${env.personalAccessToken}"
			}

			// The raw file downloaded, parse to a CV object
			val cv = json.decodeFromString<CV>(fileContents.body())


			call.response.status(fileContents.status)
			client.close()

			// TODO: 02/07/2022 refactor with CV as a single variable
			call.respond(
				FreeMarkerContent(
					template = "cvTemplate.ftl", model = mapOf(
					"user" to cv.user, "experience" to cv.experienceSection, "sections" to cv.sections
				)
				)
			)
		}

	}
}
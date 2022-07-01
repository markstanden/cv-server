package dev.markstanden.routes

import dev.markstanden.models.Cv
import dev.markstanden.models.GHContents
import io.github.cdimascio.dotenv.dotenv
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val json = Json { ignoreUnknownKeys = true }

fun Route.homepageRouting() {

	route("/form") {
		get {
			call.respond(
				FreeMarkerContent(
					template = "form.ftl", model = null
				)
			)
		}
	}

	route("/cv/{folder}") {
		get {
			// Retrieve values from ENV variables
			val env = dotenv {
				ignoreIfMissing = true
			}
			val repoName = env["REPO_NAME"] ?: "REPO_PATH env variable not set"
			val userName = env["USER_NAME"] ?: "USERNAME env variable not set"
			val pat = env["PAT"] ?: "PAT env variable not set"

			val folder = call.parameters["folder"]

			val url = "https://api.github.com/repos/$userName/$repoName/contents/$folder/cv.json"

			val client = HttpClient(CIO)

			val res = client.get(url) {
				headers["Accept"] = "application/vnd.github.v3+json"
				headers["Authorization"] = "token $pat"
			}

			val downloadURL = json.decodeFromString<GHContents>(res.body())

			val file = client.get(downloadURL.download_url) {
				headers["Accept"] = "application/vnd.github.v3+json"
				headers["Authorization"] = "token $pat"
			}

			val cv = json.decodeFromString<Cv>(file.body())

			call.response.status(file.status)
			client.close()

			call.respond(
				FreeMarkerContent(
					template = "cv.ftl", model = mapOf(
					"user" to cv.user, "experience" to cv.experienceSection, "sections" to cv.sections
				)
				)
			)
		}

	}

}
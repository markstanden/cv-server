package dev.markstanden.routes

import dev.markstanden.Files.asResource
import dev.markstanden.environment.getGithubVariables
import dev.markstanden.models.Cv
import dev.markstanden.models.GitHubAPI
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
private val env = getGithubVariables()
private val sampleCV = Json.decodeFromString(Cv.serializer(), asResource(path = "/static/sample.json")!!)


fun Route.homepageRouting() {

	route("/") {
		get {
			call.respond(
				FreeMarkerContent(
					template = "cv.ftl", model = mapOf(
					"user" to sampleCV.user, "experience" to sampleCV.experienceSection, "sections" to sampleCV.sections
				)
				)

			)
		}
	}


	route("/{folder?}") {
		get {

			val folder = call.parameters["folder"]

			val url = "https://api.github.com/repos/${env.userName}/${env.repoName}/contents/$folder/cv.json"

			val client = HttpClient(CIO)

			val res = client.get(url) {
				headers["Accept"] = "application/vnd.github.v3+json"
				headers["Authorization"] = "token ${env.personalAccessToken}"
			}

			val downloadURL = json.decodeFromString<GitHubAPI.Contents>(res.body())

			val file = client.get(downloadURL.download_url) {
				headers["Accept"] = "application/vnd.github.v3+json"
				headers["Authorization"] = "token ${env.personalAccessToken}"
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

	route("/form") {
		get {
			call.respond(
				FreeMarkerContent(
					template = "form.ftl", model = null
				)
			)
		}
	}
}
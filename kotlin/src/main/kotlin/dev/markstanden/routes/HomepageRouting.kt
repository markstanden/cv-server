package dev.markstanden.routes

import dev.markstanden.Files.asResource
import dev.markstanden.models.Cv
import dev.markstanden.models.GHTags
import io.github.cdimascio.dotenv.dotenv
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


val test = Json.decodeFromString(Cv.serializer(), asResource(path = "/static/sample.json")!!)
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
	route("/sample") {
		get {
			call.respond(
				FreeMarkerContent(
					template = "cv.ftl", model = mapOf(
					"user" to test.user, "experience" to test.experienceSection, "sections" to test.sections
				)
				)
			)
		}
	}
	route("/submit") {
		post {
			val params = call.receiveParameters()
			val customer = Json.decodeFromString<Cv>(params["data"] ?: "")
			call.respond(
				FreeMarkerContent(
					template = "cv.ftl", model = mapOf(
					"user" to customer.user, "experience" to customer.experienceSection, "sections" to customer.sections
				)
				)
			)
		}
	}
	route("/cv/{hash?}") {
		//route("/") {
		get {
			// Retrieve values from ENV variables
			val env = dotenv {
				ignoreIfMissing = true
			}
			val repoName = env["REPO_NAME"] ?: "REPO_PATH env variable not set"
			val filePath = env["FILE_PATH"] ?: "FILE_PATH env variable not set"
			val userName = env["USER_NAME"] ?: "USERNAME env variable not set"
			val pat = env["PAT"] ?: "PAT env variable not set"

			val hash = call.parameters["hash"] ?: env["HASH"] ?: "HASH env variable not set"
			val url = "https://api.github.com/repos/$userName/$repoName/tags"

			val client = HttpClient(CIO)

			val tagRes = client.get(url) {
				headers["Accept"] = "application/vnd.github.v3+json"
				headers["Authorization"] = "token $pat"
			}
			val commit = json.decodeFromString<List<GHTags>>(tagRes.body())
			val commitRes = client.get(commit[0].commit.url) {
				//headers["Accept"] = "application/vnd.github.v3+json"
				headers["Authorization"] = "token $pat"
			}
			println(commitRes.body<String>())
			call.response.status(commitRes.status)
			client.close()
		}
	}

}
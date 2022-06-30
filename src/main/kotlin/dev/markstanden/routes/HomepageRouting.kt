package dev.markstanden.routes

import dev.markstanden.Files.asResource
import dev.markstanden.models.Cv
import io.github.cdimascio.dotenv.dotenv
import io.ktor.client.*
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
		get {
			println("get to /cv/ received")
			val doten = dotenv {
				ignoreIfMissing = true
			}
			val repoPath = doten["REPO_PATH"] ?: "REPO_PATH env variable not set"
			val filePath = doten["FILE_PATH"] ?: "FILE_PATH env variable not set"
			val username = doten["USERNAME"] ?: "USERNAME env variable not set"
			val pat = doten["PAT"] ?: "PAT env variable not set"
			val hash = call.parameters["hash"] ?: doten["HASH"] ?: "HASH env variable not set"


			val client = HttpClient(CIO).use {
				val res = it.request("${repoPath}/${hash}/${filePath}")
				println(res)
			}

		}
	}

}
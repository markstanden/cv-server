package dev.markstanden.routes

import dev.markstanden.Files.asResource
import dev.markstanden.models.Cv
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


val test = Json.decodeFromString(Cv.serializer(), asResource(path = "/static/sample.json")!!)
fun Route.homepageRouting() {
	route("/") {
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
	route("/cv/{id}") {
		get {
			println("get to /cv/ received")
			val pat = dotenv {
				ignoreIfMissing = true
			}.get("PAT") ?: "Env variable not found"
			println("PAT: $pat")
//			println(${call.(id)})
		}
	}

}
package dev.markstanden.routes

import dev.markstanden.Files.asResource

import dev.markstanden.models.Cv
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import io.ktor.util.Identity.decode
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URLDecoder


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
					"user" to customer.user,
					"experience" to customer.experienceSection,
					"sections" to customer.sections
				)
				)
			)
		}
	}
	route("/cv/{id}") {
		get {
			println("get to /cv/ received")
//			println(${call.(id)})
		}
	}
}
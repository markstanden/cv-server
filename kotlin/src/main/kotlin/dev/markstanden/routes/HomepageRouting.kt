package dev.markstanden.routes

import dev.markstanden.Files.asResource

import dev.markstanden.models.Cv
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json


val test = Json.decodeFromString(Cv.serializer(), asResource(path = "/static/sample.json")!!)
fun Route.homepageRouting() {
	route("/") {
		get {
			call.respond(
				FreeMarkerContent(
					template = "cv.ftl",
					model = mapOf(
						"user" to test.user,
						"experience" to test.experienceSection,
						"sections" to test.sections
					)
				)
			)
		}
	}
}
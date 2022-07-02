package dev.markstanden.routes

import dev.markstanden.Files.asResource
import dev.markstanden.models.CV
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json

private val sampleCV = Json.decodeFromString(CV.serializer(), asResource(path = "/static/sample.json")!!)

fun Route.rootRoute() {

	route("/") {
		get {
			call.respond(
				FreeMarkerContent(
					template = "cv.ftl",
					model = mapOf(
						"user" to sampleCV.user,
						"experience" to sampleCV.experienceSection,
						"sections" to sampleCV.sections
					)
				)

			)
		}
	}
}
package dev.markstanden.routes

import dev.markstanden.Files.asResource

import dev.markstanden.models.Cv
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.graalvm.compiler.debug.TTY.printf


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
	route("/from-file") {
		post {
			val customer = call.receive<Cv>()
			println(customer)
			call.respond(
				FreeMarkerContent(
					template = "cv.ftl", model = mapOf(
					"user" to customer.user, "experience" to customer.experienceSection, "sections" to customer.sections
				)
				)
			)
		}
	}
	route("/upload") {
		post {
			println("post to /upload received")
			// retrieve all multipart data (suspending)
			val multipart = call.receiveMultipart()
			printf("All data: %s", multipart.readAllParts())
			multipart.forEachPart { part ->
				println(part.name)
				// if part is a file (could be form item)
				if (part is PartData.FileItem) {
					println(part.name)
					val data = part.provider().toString()
					println(data)
				}
				// make sure to dispose of the part after use to prevent leaks
				part.dispose()
			}
		}
	}
}
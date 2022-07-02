package dev.markstanden.routes

import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.formRoute() {
	route("/form") {
		get {
			call.respond(
				FreeMarkerContent(
					template = "form.ftl",
					model = null
				)
			)
		}
	}
}
package dev.markstanden.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.healthcheckRoute() {
	route("/healthcheck") {
		get {
			call.response.status(HttpStatusCode.OK)
			call.respond("Healthcheck OK")
		}
	}
}
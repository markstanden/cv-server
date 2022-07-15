package dev.markstanden.plugins

import dev.markstanden.datastore.GitHub
import dev.markstanden.routes.cvRoute
import dev.markstanden.routes.healthcheckRoute
import dev.markstanden.routes.sampleRoute
import dev.markstanden.routes.staticRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {

	routing {
		val path = "cv"
		healthcheckRoute()
		sampleRoute()
		cvRoute(path, GitHub())
		staticRouting()

		route("/") {
			get {
				call.respondRedirect("/$path/test")
			}
		}
	}
}
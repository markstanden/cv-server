package dev.markstanden.plugins

import dev.markstanden.routes.cvRoute
import dev.markstanden.routes.healthcheckRoute
import dev.markstanden.routes.sampleRoute
import dev.markstanden.routes.staticRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {

	install(StatusPages) {
		exception<AuthenticationException> { call, cause ->
			call.respond(HttpStatusCode.Unauthorized)
		}
		exception<AuthorizationException> { call, cause ->
			call.respond(HttpStatusCode.Forbidden)
		}

	}

	routing {
		healthcheckRoute()
		sampleRoute()
		cvRoute()
		staticRouting()
	}
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
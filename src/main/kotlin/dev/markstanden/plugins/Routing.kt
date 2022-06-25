package dev.markstanden.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import dev.markstanden.routes.homepageRouting
import dev.markstanden.routes.staticRouting


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
        homepageRouting()
        staticRouting()
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()
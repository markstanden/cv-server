package dev.markstanden.routes

import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Route.staticRouting() {
	static("/styles") {
		resources("static/styles")
	}
}
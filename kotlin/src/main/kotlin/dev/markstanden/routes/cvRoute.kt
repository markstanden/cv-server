package dev.markstanden.routes

import dev.markstanden.datastore.DataStore
import dev.markstanden.models.toMap
import dev.markstanden.userinput.sanitise
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val MAX_BRANCH_LENGTH = 20

fun Route.cvRoute(store: DataStore) {
	route("/{version}") {
		get {
			val version = call.parameters["version"].sanitise(restrictedLength = MAX_BRANCH_LENGTH)

			val (cv, status) = store.getCV(id = version)

			if (status != HttpStatusCode.OK || cv == null) {
				call.response.status(status)
				call.respond("Something went wrong")
				return@get
			}

			call.respond(
				FreeMarkerContent(
					template = "cvTemplate.ftl", model = cv.toMap()
				)
			)
		}

	}
}
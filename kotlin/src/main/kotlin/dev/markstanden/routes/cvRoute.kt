package dev.markstanden.routes

import dev.markstanden.datastore.DataStore
import dev.markstanden.userinput.hardSanitise
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import kotlin.text.toCharArray

val MAX_BRANCH_LENGTH = 20

fun Route.cvRoute(store: DataStore) {
	route("/{folder}") {
		get {
			val folder = hardSanitise(call.parameters["folder"]!!, maxLength = MAX_BRANCH_LENGTH)

			val (cv, status) = store.getCV(id = folder)

			if (status != HttpStatusCode.OK || cv == null) {
				call.response.status(status)
				call.respond("Something went wrong")
				return@get
			}

			// TODO: 02/07/2022 refactor with CV as a single variable
			call.respond(
				FreeMarkerContent(
					template = "cvTemplate.ftl", model = mapOf(
					"coverLetter" to cv.coverLetter,
					"user" to cv.user,
					"experience" to cv.experienceSection,
					"sections" to cv.sections
				)
				)
			)
		}

	}
}
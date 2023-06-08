package dev.markstanden.plugins

import dev.markstanden.datastore.DataStore
import dev.markstanden.routes.cvRoute
import dev.markstanden.routes.healthcheckRoute
import dev.markstanden.routes.sampleRoute
import dev.markstanden.routes.staticRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Application.configureRouting(store: DataStore) {
	routing {

		// Serves static assets, such as css and sample cv
		staticRouting()

		if (this.application.developmentMode) {
			sampleRoute()
		}

		cvRoute(store = store)
		healthcheckRoute()
	}
}
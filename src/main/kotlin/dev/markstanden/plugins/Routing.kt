package dev.markstanden.plugins

import dev.markstanden.datastore.DataStore
import dev.markstanden.datastore.GitHub
import dev.markstanden.routes.cvRoute
import dev.markstanden.routes.healthcheckRoute
import dev.markstanden.routes.root
import dev.markstanden.routes.sampleRoute
import dev.markstanden.routes.staticRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Application.configureRouting(baseRoute: String, store: DataStore) {
	routing {

		// Serves static assets, such as css and sample cv
		staticRouting()

		if (this.application.developmentMode) {
			sampleRoute()
		}

		root(baseRoute = baseRoute, store = GitHub())
		cvRoute(baseRoute = baseRoute, store = GitHub())
		healthcheckRoute()
	}
}
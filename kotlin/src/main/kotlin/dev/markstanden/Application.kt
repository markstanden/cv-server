package dev.markstanden

import dev.markstanden.datastore.DataStore
import dev.markstanden.datastore.GitHub
import dev.markstanden.plugins.configureRouting
import dev.markstanden.plugins.configureTemplating
import io.ktor.server.engine.*
import io.ktor.server.netty.*

val DATA_STORE: DataStore = GitHub()

fun main() {
	embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
		configureRouting(store = DATA_STORE)
		configureTemplating()
	}.start(wait = true)
}
package dev.markstanden

import dev.markstanden.plugins.configureHTTP
import dev.markstanden.plugins.configureRouting
import dev.markstanden.plugins.configureTemplating
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureHTTP()
        configureTemplating()
    }.start(wait = true)
}
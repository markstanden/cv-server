package dev.markstanden.routes

import dev.markstanden.datastore.GitHub
import dev.markstanden.plugins.configureRouting
import dev.markstanden.plugins.configureTemplating
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class HeathcheckTest {
	@Test
	fun `Internal test using test route to test rendering`() =
		testApplication {
			application {
				configureRouting(GitHub())
				configureTemplating()
			}
			client.get("/healthcheck").apply {
				assertEquals(HttpStatusCode.OK, status)
				assertTrue(this.bodyAsText().isNotEmpty())
			}
		}
}
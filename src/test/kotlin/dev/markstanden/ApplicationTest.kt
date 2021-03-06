package dev.markstanden

import dev.markstanden.datastore.GitHub
import dev.markstanden.plugins.configureRouting
import dev.markstanden.plugins.configureTemplating
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ApplicationTest {
	@Test
	fun testGHApiSetup() =
		testApplication {
			application {
				configureRouting("test", GitHub())
				configureTemplating()
			}
			client.get("/test/full").apply {
				assertEquals(HttpStatusCode.OK, status)
			}
		}
}
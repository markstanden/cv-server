package dev.markstanden

import dev.markstanden.plugins.configureHTTP
import dev.markstanden.plugins.configureRouting
import dev.markstanden.plugins.configureTemplating
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ApplicationTest {
	@Test
	fun testRoot() =
		testApplication {
			application {
				configureRouting()
				configureHTTP()
				configureTemplating()
			}
			client.get("/").apply {
				assertEquals(HttpStatusCode.OK, status)
				assertTrue(bodyAsText().contains("first second"))
			}
		}

	@Test
	fun testGHApi() =
		testApplication {
			application {
				configureRouting()
				configureHTTP()
				configureTemplating()
			}
			client.get("/base").apply {
				assertEquals(HttpStatusCode.OK, status)
			}
		}
}
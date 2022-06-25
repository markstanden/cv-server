package dev.markstanden.routes

import dev.markstanden.models.Cv
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import java.nio.file.Files
import java.nio.file.Path

val fileString: String = Files.readString(Path.of("/home/mark/Documents/Coding/cv-server/src/main/resources/static/sample.json"))
val test = Json.decodeFromString(Cv.serializer(), fileString)
fun Route.homepageRouting() {
    route("/") {
        get {
            call.respond(

                    FreeMarkerContent(
                            "cv.ftl", mapOf(
                            "user" to "user"
                    )
                    )
            )
        }
    }
}
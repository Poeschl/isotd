package io.github.poeschl.isotd.rest

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty

fun main(args: Array<String>) {
    embeddedServer(Jetty, 8000) {
        routing {
            get("/") {
                val userAgent = call.request.headers["user-agent"] ?: ""

                call.respondText("Hello $userAgent", contentType = ContentType.Text.Plain)
            }
        }
    }.start(wait = true)
}

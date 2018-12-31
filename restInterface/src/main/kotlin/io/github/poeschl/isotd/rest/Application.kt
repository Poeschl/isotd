package io.github.poeschl.isotd.rest

import io.github.poeschl.isotd.rest.dagger.RestInterfaceProviderComponent
import io.ktor.application.call
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty

fun main(args: Array<String>) {

    val restProviderComponent = RestInterfaceProviderComponent.init()
    val shortcutController = restProviderComponent.shortcutController

    embeddedServer(Jetty, 8080) {
        routing {
            get("/") {
                shortcutController.provideRandomShortcut(call)
            }
        }
    }.start(wait = true)
}

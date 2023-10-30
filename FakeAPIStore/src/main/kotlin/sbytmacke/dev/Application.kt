package sbytmacke.dev

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import sbytmacke.dev.plugins.*

fun main() {
    // 0.0.0.0 para escuchar a todas las interfaces de red
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}

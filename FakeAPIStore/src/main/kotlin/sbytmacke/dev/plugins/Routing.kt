package sbytmacke.dev.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import sbytmacke.dev.routes.productRouting

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Raíz de Fake Store API", status = HttpStatusCode.OK)
        }
        productRouting()
    }
}

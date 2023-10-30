package sbytmacke.dev.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import sbytmacke.dev.models.Product

fun Route.productRouting() {
    route("/product") {
        get {
            if (listProducts.isNotEmpty()) {
                call.respond(listProducts) // Al tener el @Serializable se devuelve como JSON
            } else {
                call.respondText("No hay productos disponibles", status = HttpStatusCode.OK)
            }
        }
        get("{uuid?}") {
            val uuid = call.parameters["uuid"] ?: return@get call.respondText(
                "UUID no encontrado",
                status = HttpStatusCode.NotFound
            )
            val product = listProducts.find { it.uuid == uuid } ?: return@get call.respondText(
                "Producto no encontrado",
                status = HttpStatusCode.NotFound
            )
            call.respond(product)
        }
        post {
            val product = call.receive<Product>() // Recibimos un JSON y se transforma a USER
            listProducts.add(product)
            call.respondText("Producto añadido correctamente", status = HttpStatusCode.Created)
        }
        /*
        Para crear un producto desde tu aplicación y enviar la creación a tu servicio Ktor,
        debes realizar una solicitud HTTP POST desde tu aplicación hacia la URL de tu servicio Ktor.
        Esto se puede hacer utilizando una biblioteca o módulo que sea capaz de realizar solicitudes HTTP.
        Aquí te mostraré un ejemplo en Android utilizando la biblioteca OkHttp,
        que es comúnmente utilizada para realizar solicitudes HTTP:
        Asegúrate de que la biblioteca OkHttp esté agregada a tu proyecto Android.

        import okhttp3.MediaType
        import okhttp3.OkHttpClient
        import okhttp3.Request
        import okhttp3.RequestBody

        // Define el JSON que deseas enviar como cuerpo de la solicitud
        val json = """
            {
                "ID": "nuevoID",
                "SecondaryID": "nuevoSecondaryID",
                "Nombre": "Nuevo Producto",
                "Precio": 29.99,
                "Categoría": "Electrónica",
                "Descripción": "Nuevo producto añadido",
                "Imagen": "imagen_nueva.jpg",
                "URL": "http://ejemplo.com/nuevo_producto"
            }
        """.trimIndent()

        // Define la URL de tu servicio Ktor
        val url = "http://127.0.0.1:8080/product"

        // Configura una solicitud POST con el cuerpo JSON
        val mediaType = MediaType.parse("application/json; charset=utf-8")
        val requestBody = RequestBody.create(mediaType, json)
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        // Crea un cliente OkHttpClient y ejecuta la solicitud
        val client = OkHttpClient()
        val response = client.newCall(request).execute()

        // Verifica la respuesta
        if (response.isSuccessful) {
            // La solicitud fue exitosa, puedes manejar la respuesta aquí
        } else {
            // Hubo un error en la solicitud
            // response.body()?.string() te dará más detalles sobre el error
        }
        */
        delete("{uuid?}") {
            val uuid = call.parameters["uuid"] ?: return@delete call.respondText(
                "UUID no encontrado",
                status = HttpStatusCode.NotFound
            )
            if (listProducts.removeIf { it.uuid == uuid }) {
                call.respondText("Producto eliminado correctamente", status = HttpStatusCode.OK)
            } else {
                call.respondText("Producto no encontrado", status = HttpStatusCode.NotFound)
            }
        }
    }
}

// Llamar a la base de datos de MongoDB
private val listProducts = mutableListOf(
    Product(
        "aeb2c8e4-29d2-4311-8ae7-653c71b719e9",
        "P12345",
        "HDMI Switch Sony",
        19.99f,
        "Electrónica",
        "Un producto de alta calidad para entusiastas de la tecnología.",
        "imagen1.jpg",
        "http://ejemplo.com/productoA"
    ),
    Product(
        "7cfb4d67-9c1a-4a38-94b0-86fe327f7a7e",
        "P67890",
        "Camisa Azul RalphLaurent",
        39.99f,
        "Ropa",
        "Ropa elegante y cómoda para todas las ocasiones.",
        "imagen2.jpg",
        "http://ejemplo.com/productoB"
    ),
    Product(
        "6e4c89fc-7c6e-4f08-8342-87e36f3c3e16",
        "P54321",
        "Alfombra Rollie",
        9.99f,
        "Hogar",
        "Accesorios para el hogar que hacen la vida más fácil.",
        "imagen3.jpg",
        "http://ejemplo.com/productoC"
    ),
    Product(
        "71b50f5a-59f0-471a-9ebc-3e8b6a7f0a04",
        "P98765",
        "Coche teledirigido Razer",
        29.99f,
        "Juguetes",
        "Divertido juguete para niños y niñas.",
        "imagen4.jpg",
        "http://ejemplo.com/productoD"
    ),
    Product(
        "fdecc8a7-6f22-4d07-8c20-7546fca7e2aa",
        "P24680",
        "Pendrive 32GB KingStone",
        14.99f,
        "Electrónica",
        "Un dispositivo esencial para la vida moderna.",
        "imagen5.jpg",
        "http://ejemplo.com/productoE"
    ),
    Product(
        "1d1c4a7e-803f-4f20-8c55-2a046b52e2bc",
        "P13579",
        "Balenciaga Polo",
        49.99f,
        "Ropa",
        "Ropa de alta gama para ocasiones especiales.",
        "imagen6.jpg",
        "http://ejemplo.com/productoF"
    ),
    Product(
        "3b6549b6-ef6a-4d6a-9e5d-573ac2f13543",
        "P15984",
        "Aspiradora BOSCH",
        12.99f,
        "Hogar",
        "Herramientas útiles para el hogar.",
        "imagen7.jpg",
        "http://ejemplo.com/productoG"
    ),
    Product(
        "8a937fc8-2cda-48a1-8e77-9a7d09be49df",
        "P65521",
        "Peluche Oso IKEA",
        7.99f,
        "Juguetes",
        "Juguete educativo para niños en edad preescolar.",
        "imagen8.jpg",
        "http://ejemplo.com/productoH"
    ),
    Product(
        "0e6b1a8d-c0c2-4f34-8d8a-3e9f6cfd8e7e",
        "P36589",
        "Mando PS5 Sony",
        59.99f,
        "Electrónica",
        "Tecnología de vanguardia para entusiastas.",
        "imagen9.jpg",
        "http://ejemplo.com/productoI"
    ),
    Product(
        "5c4d89ae-91f7-4a58-8c92-7e4b6f3c3e2d",
        "P40023",
        "Pantalón de algodón H&M",
        24.99f,
        "Ropa",
        "Ropa casual y cómoda para el día a día.",
        "imagen10.jpg",
        "http://ejemplo.com/productoJ"
    ),
    Product(
        "d1b5cf6e-73f6-4b0a-8a5f-1e9e4d6b2a9c",
        "P57894",
        "Fregona retráctil BOSCH",
        11.99f,
        "Hogar",
        "Productos esenciales para el hogar.",
        "imagen11.jpg",
        "http://ejemplo.com/productoK"
    ),
    Product(
        "2b4d3a1c-7e6c-4f80-8c75-6a1b45c1e2fa",
        "P63546",
        "Ray McQueen HotWheels",
        19.99f,
        "Juguetes",
        "Juguetes divertidos para todas las edades.",
        "imagen12.jpg",
        "http://ejemplo.com/productoL"
    ),
    Product(
        "e9bc8d8e-63a5-4d30-8c50-1a0b3c7d6e7b",
        "P74897",
        "Ratón inalámbrico Logitech Hero",
        34.99f,
        "Electrónica",
        "Dispositivo versátil para tu estilo de vida digital.",
        "imagen13.jpg",
        "http://ejemplo.com/productoM"
    ),
    Product(
        "c8d7a4e1-6c8e-4f8a-8d8c-4a9d0a7d8c4e",
        "P88964",
        "Calcetines de lana GUCCI",
        16.99f,
        "Ropa",
        "Ropa a la moda para jóvenes y adultos.",
        "imagen14.jpg",
        "http://ejemplo.com/productoN"
    ),
    Product(
        "4e7b9c3f-7f6a-4d6c-9e5e-6c7c2a1f8b5c",
        "P54896",
        "Doble elefante telepata de guerra LEGO",
        8.99f,
        "Juguetes",
        "Productos para mantener tu hogar ordenado.",
        "imagen15.jpg",
        "http://ejemplo.com/productoO"
    )
)

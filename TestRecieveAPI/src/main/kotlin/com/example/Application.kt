package com.example

import com.example.plugins.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import okhttp3.*
import java.io.IOException

fun main() {
    val client = OkHttpClient()
    //val fakeStoreAPI = "http://127.0.0.1:8080"  // Conexión a la API
    val fakeStoreAPI = "http://api-fake-store:8080" // Al utilizar docker tendremos que hacer una red y conectarnos al name del servicio
    
    // Ejemplo de solicitud GET para obtener datos
    val getRequest = Request.Builder()
        .url("$fakeStoreAPI/product")
        .build()

    client.newCall(getRequest).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println("Error al obtener datos: $e")
        }

        override fun onResponse(call: Call, response: Response) {
            val responseData = response.body?.string()
            println("Datos recibidos: $responseData")
        }
    })

    // Ejemplo de solicitud POST para enviar datos
    /*    val postData = "key1=valor1&key2=valor2".toRequestBody()
        val postRequest = Request.Builder()
            .url("$fakeStoreAPI/ruta/crear")
            .post(postData)
            .build()

        client.newCall(postRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Error al crear datos: $e")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                println("Datos creados: $responseData")
            }
        })

        // Ejemplo de solicitud DELETE para eliminar datos
        val deleteRequest = Request.Builder()
            .url("$fakeStoreAPI/ruta/eliminar/123")
            .delete()
            .build()

        client.newCall(deleteRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Error al eliminar datos: $e")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                println("Datos eliminados: $responseData")
            }
        })*/
}

fun mainAsService() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.2", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
}

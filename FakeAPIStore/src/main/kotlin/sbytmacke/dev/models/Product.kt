package sbytmacke.dev.models

import kotlinx.serialization.Serializable

// Serializable para facilitar la conversión a JSON
@Serializable
data class Product(
    val uuid: String,
    val secondaryId: String,
    val name: String,
    val price: Float,
    val category: String,
    val description: String,
    val image: String,
    val url: String
)


package dev.markstanden.models

@kotlinx.serialization.Serializable
data class Business(
        val title: String,
        val link: String,
        val location: Location,
        val department: String,
) {

}
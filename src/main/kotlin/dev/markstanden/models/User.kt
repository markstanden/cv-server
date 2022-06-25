package dev.markstanden.models

@kotlinx.serialization.Serializable
data class User(

        val name: String,
        val location: Location,
        val contact: Contact,
        val summary: String,
) {}
package dev.markstanden.models

@kotlinx.serialization.Serializable
data class Item(
        val title: String, val content: List<String> = emptyList(), val dates: String = ""
)
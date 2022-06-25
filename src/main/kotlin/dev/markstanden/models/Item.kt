package dev.markstanden.models

@kotlinx.serialization.Serializable
data class Item(
        val title: String, val content: String = "", val subItems: List<Item> = emptyList(), val dates: String = "", val business: Business?
)
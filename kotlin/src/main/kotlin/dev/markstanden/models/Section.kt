package dev.markstanden.models

@kotlinx.serialization.Serializable
data class Section(val title: String, val items: List<Item>)
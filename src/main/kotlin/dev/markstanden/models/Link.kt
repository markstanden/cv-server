package dev.markstanden.models

@kotlinx.serialization.Serializable
data class Link(
	val title: String,
	val url: String,
)
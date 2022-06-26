package dev.markstanden.models

@kotlinx.serialization.Serializable
class Position(val title: String, val content: List<String>, val dates: String, val business: Business)
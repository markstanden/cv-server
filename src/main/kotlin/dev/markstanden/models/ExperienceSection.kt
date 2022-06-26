package dev.markstanden.models

@kotlinx.serialization.Serializable
data class ExperienceSection(val title: String, val items: List<Position>)
package dev.markstanden.models

@kotlinx.serialization.Serializable
data class Cv(val user: User, val sections: List<Section>)
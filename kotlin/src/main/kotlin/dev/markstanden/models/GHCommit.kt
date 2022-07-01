package dev.markstanden.models

@kotlinx.serialization.Serializable
data class GHCommit(val sha: String, val url: String)
package dev.markstanden.models

@kotlinx.serialization.Serializable
data class GHContents(val sha: String, val download_url: String)
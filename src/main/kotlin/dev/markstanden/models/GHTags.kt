package dev.markstanden.models

@kotlinx.serialization.Serializable
data class GHTags(
	val name: String, val zipball_url: String, val tarball_url: String, val commit: GHCommit, val node_id: String,
)
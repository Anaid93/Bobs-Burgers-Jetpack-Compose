package com.example.movies.model

import kotlinx.serialization.Serializable


@Serializable
data class Movies(
    val id: Int,
    val name: String,
    val occupation: String? = "Unknown",
    val firstEpisode: String? = "Unknown",
    val voicedBy: String? = "Unknown",
    val image: String
)

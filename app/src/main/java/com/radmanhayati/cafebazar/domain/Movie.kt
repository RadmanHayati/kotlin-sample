package com.radmanhayati.cafebazar.domain


data class Movie(
    val id: Int,
    val releaseDate: String? = "",
    val name: String,
    val originalTitle: String,
    val originalLanguage: String,
    val overview: String,
    val posterPath: String?,
    val voteAverage: Double,
    val voteCount: Int,
)
package com.radmanhayati.cafebazar.data.remote


data class MovieDto(
    val id: Int,
    val release_date: String? = "",
    val title: String,
    val original_title: String,
    val original_language: String,
    val overview: String,
    val poster_path: String?,
    val vote_average: Double,
    val vote_count: Int,
)
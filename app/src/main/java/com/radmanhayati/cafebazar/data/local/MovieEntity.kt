package com.radmanhayati.cafebazar.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val releaseDate: String? = "",
    val name: String,
    val originalTitle: String,
    val originalLanguage: String,
    val overview: String,
    val posterPath: String?,
    val voteAverage: Double,
    val voteCount: Int,
    @ColumnInfo(name = "page")
    var page: Int,
)
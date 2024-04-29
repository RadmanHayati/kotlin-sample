package com.radmanhayati.cafebazar.data.mappers

import com.radmanhayati.cafebazar.data.local.MovieEntity
import com.radmanhayati.cafebazar.data.remote.MovieDto
import com.radmanhayati.cafebazar.domain.Movie

fun MovieDto.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        name = title,
        releaseDate = release_date,
        originalTitle = original_title,
        originalLanguage = original_language,
        overview = overview,
        posterPath =  "https://image.tmdb.org/t/p/original$poster_path", // todo : replace with String extension function
        voteAverage = vote_average,
        voteCount = vote_count,
        page = 0
    )
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        id = id,
        name = name,
        releaseDate = releaseDate,
        originalTitle = originalTitle,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = posterPath,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

package com.nadiaguerra.examen_unidad4.models

data class MoviesModel(
    val Search: List<MovieItem>,
    val totalResults:String,
    val Response: String
)

data class MovieItem(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Poster: String,
)

data class MovieDetails(
    val Title: String,
    val Year: String,
    val Genre: String,
    val Director: String,
    val Actors: String,
    val Plot: String,
    val Poster: String,
    val imdbRating: String,
    val Ratings: List<Rating>,
    val imdbID: String
)

data class Rating(
    val Source: String,
    val Value: String
)


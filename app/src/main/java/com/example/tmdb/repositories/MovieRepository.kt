package com.example.tmdb.repositories

import com.example.tmdb.api.TmdbApi
import com.example.tmdb.responses.TmdbMovie

class MovieRepository(private val api: TmdbApi) : BaseRepository() {

    suspend fun getPopularMovies(): MutableList<TmdbMovie>? {
        val movieResponse = safeApiCall(
            call = { api.getPopularMovie().await() },
            errorMessage = "Error Fetching Popular Movies"
        )

        return movieResponse?.results?.toMutableList()
    }
}
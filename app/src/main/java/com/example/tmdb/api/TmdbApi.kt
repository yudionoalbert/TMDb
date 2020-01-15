package com.example.tmdb.api

import com.example.tmdb.responses.TmdbMovieResponse
import com.example.tmdb.utils.MOVIE_POPULAR
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface TmdbApi{

    @GET(MOVIE_POPULAR)
    fun getPopularMovie(): Deferred<Response<TmdbMovieResponse>>
}
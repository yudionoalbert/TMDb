package com.example.tmdb.utils

import com.example.tmdb.api.TmdbApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory {

    val tmdbApi: TmdbApi
    private val authInterceptor: Interceptor
    private val tmdbClient: OkHttpClient

    init {
        authInterceptor = Interceptor { chain ->
            val newUrl = chain.request().url()
                .newBuilder()
                .addQueryParameter("api_key", tmdbApiKey)
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }

        tmdbClient = OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .build()

        tmdbApi = initRetrofit().create(TmdbApi::class.java)
    }

    private fun initRetrofit(): Retrofit = Retrofit.Builder()
        .client(tmdbClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}
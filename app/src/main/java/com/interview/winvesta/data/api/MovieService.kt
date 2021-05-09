package com.interview.winvesta.data.api

import com.interview.winvesta.BuildConfig
import com.interview.winvesta.data.model.PopularMovieResponse
import com.interview.winvesta.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Used to connect to the The movie DB API to fetch photos
 */
interface MovieService {
    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Query("sort_by") query: String,
        @Query("page") page: Int,
        @Query("api_key") clientId: String = BuildConfig.MOVIE_API_ACCESS_KEY
    ): PopularMovieResponse

    companion object {

        fun create(): MovieService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService::class.java)
        }
    }
}
package com.yogeshandroid.mvvm_daggerhilt_retrofit.network

import com.yogeshandroid.mvvm_daggerhilt_retrofit.models.RandomDogResp
import com.yogeshandroid.mvvm_daggerhilt_retrofit.views.fragments.home.models.TopMoviesResp
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("random")
    fun getRandomDogs(): Call<RandomDogResp>


    @GET("movie")
    fun topMovies(
        @Header("Authorization") token: String,
        @Query("include_adult") include_adult: Boolean,
        @Query("include_video") include_video: Boolean,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("sort_by") sort_by: String
    ): Call<TopMoviesResp>
}
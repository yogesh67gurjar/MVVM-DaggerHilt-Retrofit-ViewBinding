package com.yogeshandroid.mvvm_daggerhilt_retrofit.network

import com.yogeshandroid.mvvm_daggerhilt_retrofit.models.RandomDogResp
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("random")
    fun getRandomDogs(): Call<RandomDogResp>
}
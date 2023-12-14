package com.yogeshandroid.mvvm_daggerhilt_retrofit.repository

import com.yogeshandroid.mvvm_daggerhilt_retrofit.models.RandomDogResp
import com.yogeshandroid.mvvm_daggerhilt_retrofit.network.ApiListener
import com.yogeshandroid.mvvm_daggerhilt_retrofit.network.ApiService
import com.yogeshandroid.mvvm_daggerhilt_retrofit.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class RandomRepository @Inject constructor(var apiService: ApiService) {


    fun getRandomDogImages(apiListener: ApiListener) {

        apiService.getRandomDogs().enqueue(object : Callback<RandomDogResp> {
            override fun onResponse(call: Call<RandomDogResp>, response: Response<RandomDogResp>) {
                if (response.isSuccessful) {
                    apiListener.onResponse(response.body() as Any, "random")
                } else {
                    response.errorBody()?.let {
                        Utils.getServerError(response.code(), it)?.let {
                            apiListener.onFailure(
                                it
                            )
                        }
                    }
                }
            }

            override fun onFailure(call: Call<RandomDogResp>, t: Throwable) {
                apiListener.onFailure(t.message.toString())
            }

        })

    }

}
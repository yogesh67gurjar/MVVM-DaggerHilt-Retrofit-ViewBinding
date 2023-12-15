package com.yogeshandroid.mvvm_daggerhilt_retrofit.views.fragments.home.repository

import com.yogeshandroid.mvvm_daggerhilt_retrofit.network.ApiListener
import com.yogeshandroid.mvvm_daggerhilt_retrofit.network.ApiService
import com.yogeshandroid.mvvm_daggerhilt_retrofit.utils.Constants
import com.yogeshandroid.mvvm_daggerhilt_retrofit.utils.Utils
import com.yogeshandroid.mvvm_daggerhilt_retrofit.views.fragments.home.models.TopMoviesResp
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(val apiService: ApiService) {

    fun topMovies(
        includeAdult: Boolean,
        includeVideo: Boolean,
        lang: String,
        page: Int,
        sortBy: String,
        token: String,
        apiListener: ApiListener
    ) {
        apiService.topMovies(token, includeAdult, includeVideo, lang, page, sortBy)
            .enqueue(object : retrofit2.Callback<TopMoviesResp> {
                override fun onResponse(
                    call: Call<TopMoviesResp>,
                    response: Response<TopMoviesResp>
                ) {
                    if (response.isSuccessful) {
                        apiListener.onResponse(response.body() as Any, Constants.TOP_MOVIES)
                    } else {
                        response.errorBody()?.let {
                            Utils.getServerError(response.code(), it)
                                ?.let { apiListener.onFailure(it) }
                        }
                    }
                }

                override fun onFailure(call: Call<TopMoviesResp>, t: Throwable) {
                    apiListener.onFailure(t.message.toString())
                }

            })
    }
}
package com.yogeshandroid.mvvm_daggerhilt_retrofit.views.fragments.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yogeshandroid.mvvm_daggerhilt_retrofit.network.ApiListener
import com.yogeshandroid.mvvm_daggerhilt_retrofit.utils.Constants
import com.yogeshandroid.mvvm_daggerhilt_retrofit.views.fragments.home.models.TopMoviesResp
import com.yogeshandroid.mvvm_daggerhilt_retrofit.views.fragments.home.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private var homeRepository: HomeRepository) : ViewModel() {

    var topMoviesResp: MutableLiveData<TopMoviesResp> = MutableLiveData<TopMoviesResp>()
    var failureString: MutableLiveData<String> = MutableLiveData<String>()

    var apiListener: ApiListener = object : ApiListener {
        override fun onResponse(objects: Any, type: String) {
            if (type == Constants.TOP_MOVIES) {
                topMoviesResp.value = objects as TopMoviesResp
            }
        }

        override fun onFailure(string: String) {
            failureString.value = string
        }

    }

    fun topMovies(
        includeAdult: Boolean,
        includeVideo: Boolean,
        lang: String,
        page: Int,
        sortBy: String,
        token: String,
    ) {
        homeRepository.topMovies(includeAdult, includeVideo, lang, page, sortBy,token, apiListener)
    }

}
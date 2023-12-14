package com.yogeshandroid.mvvm_daggerhilt_retrofit.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yogeshandroid.mvvm_daggerhilt_retrofit.models.RandomDogResp
import com.yogeshandroid.mvvm_daggerhilt_retrofit.network.ApiListener
import com.yogeshandroid.mvvm_daggerhilt_retrofit.repository.RandomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(var randomRepository: RandomRepository) : ViewModel() {


    var randomData: MutableLiveData<RandomDogResp> = MutableLiveData<RandomDogResp>()
    var failureString: MutableLiveData<String> = MutableLiveData<String>()


    private var apiListener: ApiListener = (object : ApiListener {
        override fun onResponse(objects: Any, type: String) {
            if (type == "random") {
                randomData.value = objects as RandomDogResp
            }
        }

        override fun onFailure(string: String) {
            failureString.value = string
        }
    })

    fun getRandomDogImages() {
        randomRepository.getRandomDogImages(apiListener)
    }


}
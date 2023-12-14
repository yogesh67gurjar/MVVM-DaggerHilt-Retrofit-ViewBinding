package com.yogeshandroid.mvvm_daggerhilt_retrofit.network

import java.util.Objects
import javax.inject.Inject


interface ApiListener {
    fun onResponse(objects: Any, type: String)
    fun onFailure(string: String)
}
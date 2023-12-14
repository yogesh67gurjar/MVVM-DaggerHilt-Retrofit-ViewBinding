package com.yogeshandroid.mvvm_daggerhilt_retrofit.utils

import com.google.gson.GsonBuilder
import com.yogeshandroid.mvvm_daggerhilt_retrofit.models.CommonErrorResponse
import okhttp3.ResponseBody
import java.io.IOException

class Utils {

    companion object {
        @JvmStatic
        fun getServerError(responseCode: Int, responseBody: ResponseBody): String? {
            var message = ""
            val gson = GsonBuilder().create()
            try {
                val commonErrorResponse: CommonErrorResponse = gson.fromJson(
                    responseBody.string(),
                    CommonErrorResponse::class.java
                )
                if (responseCode == 400) {
                    message = commonErrorResponse.message
                } else if (responseCode == 401) {
                    message = commonErrorResponse.message
                } else if (responseCode == 500) {
                    message = commonErrorResponse.message
                } else if (responseCode == 404) {
                    message = commonErrorResponse.message
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return message
        }
    }
}
package com.yogeshandroid.mvvm_daggerhilt_retrofit.appModule

import com.yogeshandroid.mvvm_daggerhilt_retrofit.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Named("baseUrlTmdb")
    fun providesBaseUrl(): String {
        return "https://api.themoviedb.org/3/discover/"
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Provides
    fun providesRetrofitClient(@Named("baseUrlTmdb") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}
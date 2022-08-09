// Retrofit 빌더
package com.example.tour.api

import com.example.tour.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitObject {
    private fun getRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL_VACCINE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService():APIService {
        return getRetrofit().create(APIService::class.java)
    }
}
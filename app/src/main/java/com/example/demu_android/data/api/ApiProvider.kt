package com.example.demu_android.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {
    private const val BASE_URL = "https://3.37.219.136:8080"
    private var retrofit: Retrofit? = null

    private fun getApiInstance(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit!!
    }

    fun getAuthApi(): AuthApi {
        return getApiInstance().create(AuthApi::class.java)
    }
}
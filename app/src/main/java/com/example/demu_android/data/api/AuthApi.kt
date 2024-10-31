package com.example.demu_android.data.api

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/public/signup")
    fun signup() {

    }
}
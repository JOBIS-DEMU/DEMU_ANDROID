package com.example.demu_android.data.api

import com.example.demu_android.data.request.auth.SignUpRequest
import com.example.demu_android.data.response.auth.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/public/signup")
    fun signUp(
        @Body request: SignUpRequest
    ): Call<SignUpResponse>
}
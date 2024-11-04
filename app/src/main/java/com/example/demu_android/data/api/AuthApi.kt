package com.example.demu_android.data.api

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.demu_android.data.request.auth.LoginRequest
import com.example.demu_android.data.request.auth.SignUpRequest
import com.example.demu_android.data.response.auth.LoginResponse
import com.example.demu_android.data.response.auth.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {
    @POST("/public/signup")
    fun signUp(
        @Body request: SignUpRequest
    ): Call<SignUpResponse>

    @POST("/public/signin")
    fun login(
        @Body request: LoginRequest
    ): Call<LoginResponse>

    @GET("/public/password/find/{email}")
    fun findPassword(
        @Path("email") email: String
    )
}
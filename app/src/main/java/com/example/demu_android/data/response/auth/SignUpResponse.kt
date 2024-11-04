package com.example.demu_android.data.response.auth

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
)

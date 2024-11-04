package com.example.demu_android.data.response.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("access_expires_at") val accessExpiresAt: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("refresh_expires_at") val refreshExpiresAt: String,
)

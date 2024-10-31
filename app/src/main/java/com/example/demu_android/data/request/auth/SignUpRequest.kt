package com.example.demu_android.data.request.auth

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("accountId") val accountId: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("password") val password: String,
)

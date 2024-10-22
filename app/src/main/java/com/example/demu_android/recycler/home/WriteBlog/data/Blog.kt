package com.example.demu_android.recycler.home.WriteBlog.data

import com.example.demu_android.utils.Tier

data class Blog(
    val id: Long,
    val nickname : String,
    val title : String,
    val content : String,
    val recommend : Long,
    val grade : Enum<Tier>
)

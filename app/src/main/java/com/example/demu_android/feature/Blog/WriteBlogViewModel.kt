package com.example.demu_android.feature.Blog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demu_android.feature.recycler.home.WriteBlog.data.Blog

class WriteBlogViewModel : ViewModel() {
    private val _blogList: MutableLiveData<List<Blog>> = MutableLiveData()
    var blogList: LiveData<List<Blog>> =_blogList

    fun addBlogList(blog : List<Blog>) {
        _blogList.value = blog
    }
}
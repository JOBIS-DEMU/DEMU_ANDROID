package com.example.demu_android.Blog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demu_android.R
import com.example.demu_android.recycler.home.WriteBlog.data.Blog
import com.example.demu_android.utils.Tier

class WriteBlogFragment : Fragment() {
    private val blogList: MutableList<Blog> = mutableListOf(
        Blog(1092L, "", "", "", 12092L, "", Tier.GRADE)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wrtie_blog, container, false)
    }
}
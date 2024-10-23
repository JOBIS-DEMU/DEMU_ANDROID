package com.example.demu_android.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.demu_android.databinding.FragmentHomeBinding
import com.example.demu_android.recycler.home.WriteBlog.WriteBlogAdaptor
import com.example.demu_android.recycler.home.WriteBlog.data.Blog

class HomeFragment : Fragment(), View.OnClickListener {
    private val blogList: MutableList<Blog> = mutableListOf(
        // Blog(1092L, "", "", "", 12092L, Enum<Tier.GRADE, "">)
    )
    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private lateinit var recyclerview: WriteBlogAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onClick(v: View?) {

    }

    private fun setListAdapter() {
//        blogList.add(
//            Blog(
//            1028,
//            "",
//            binding.etTitle.text.toString(),
//            "",
//            19,
//            Tier.GRADE
//          )
//        )
    }

    private fun recyclerAdapter() {
        recyclerview = WriteBlogAdaptor(blogList)


    }
}
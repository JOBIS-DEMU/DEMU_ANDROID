package com.example.demu_android.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demu_android.Blog.WriteBlogViewModel
import com.example.demu_android.databinding.FragmentHomeBinding
import com.example.demu_android.recycler.home.WriteBlog.WriteBlogAdaptor
import com.example.demu_android.recycler.home.WriteBlog.data.Blog
import com.example.demu_android.type.Major

class HomeFragment : Fragment(), View.OnClickListener {
    private val blogList: MutableList<Blog> = mutableListOf(
        Blog("안녕하세요", "xptmxmdlqslek", Major.ANDROID)
    )
    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val writeBlogViewModel by lazy {
        ViewModelProvider(this@HomeFragment)[WriteBlogViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val result = arguments?.getString("blogTitle")

        result?.let { Blog(it, "afdsa", Major.ANDROID) }?.let {
            blogList.add(
                it
            )
            writeBlogViewModel.addBlogList(blogList)
        }

        Log.d("TEST", blogList.toString())

        setAddBlogList()
        observeBlogList()
    }

    override fun onClick(v: View?) {

    }

    private fun setAddBlogList() {
        writeBlogViewModel.addBlogList(blogList)
    }

    private fun observeBlogList() {
        writeBlogViewModel.blogList.observe(this) {
            val writeBlogAdaptor = WriteBlogAdaptor(it)
            val layoutManager = GridLayoutManager(requireContext(), 1)
            binding.recycler.layoutManager = layoutManager
            binding.recycler.adapter = writeBlogAdaptor
        }
    }
}
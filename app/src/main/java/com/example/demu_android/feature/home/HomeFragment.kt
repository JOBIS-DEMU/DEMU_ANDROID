package com.example.demu_android.feature.home

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demu_android.feature.Blog.WriteBlogViewModel
import com.example.demu_android.databinding.FragmentHomeBinding
import com.example.demu_android.feature.recycler.home.WriteBlog.WriteBlogAdaptor
import com.example.demu_android.feature.recycler.home.WriteBlog.data.Blog
import com.example.demu_android.feature.type.Major

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

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
        if (uris.isNotEmpty()) {
            Log.d("TEST", "Number of items selected: ${uris.size}")
            loadImage(uris[0])
        } else {
            Log.d("PhotoPicker", "No media selected")
        }

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

        arguments?.let {
            val blogTitle = it.getString("blogTitle", "")
            val blogContent = it.getString("blogContent", "")
            Log.d("TEST",blogTitle)
            if (blogTitle.isNotEmpty() && blogContent.isNotEmpty()) {
                blogList.add(Blog(blogTitle, blogContent, Major.ANDROID))
            }
        }

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
            writeBlogAdaptor.notifyDataSetChanged()
            binding.recycler.layoutManager = layoutManager
            binding.recycler.adapter = writeBlogAdaptor
        }
    }

    private fun loadImage(uri: Uri) {
        Glide.with(this)
            .load(uri)
            .into(activityMainBinding.imgListImg)
    }
}
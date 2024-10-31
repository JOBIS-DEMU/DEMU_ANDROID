package com.example.demu_android.feature.recycler.home.WriteBlog

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demu_android.databinding.ListBlogItemBinding
import com.example.demu_android.feature.recycler.home.WriteBlog.data.Blog

class
WriteBlogAdaptor(
    private val items: List<Blog>,
) : RecyclerView.Adapter<WriteBlogAdaptor.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListBlogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    class Holder(
        private val binding: ListBlogItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(blog: Blog) {
            Log.d("TEST", binding.blog.toString())
            binding.blog = blog
            binding.tvItemTitle.text = blog.title
        }
    }
}
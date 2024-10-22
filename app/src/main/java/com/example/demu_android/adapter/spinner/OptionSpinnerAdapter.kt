package com.example.demu_android.adapter.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import com.example.demu_android.databinding.ListSpinnerOptionBinding

class OptionSpinnerAdapter(context: Context, @LayoutRes private val resId: Int, private val menuList: List<String>)
    : ArrayAdapter<String>(context, resId, menuList) {

    override fun getView(position: Int, converView: View?, parent: ViewGroup): View {
        val binding = ListSpinnerOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.tvMajorTitle.text = menuList[position]

        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ListSpinnerOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.tvMajorTitle.text = menuList[position]

        return binding.root
    }

    override fun getCount() = menuList.size
}
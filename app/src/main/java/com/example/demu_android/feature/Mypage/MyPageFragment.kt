package com.example.demu_android.feature.Mypage

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.demu_android.R
import com.example.demu_android.databinding.ActivityLoginBinding
import com.example.demu_android.databinding.FragmentMyPageBinding


class MyPageFragment : Fragment() {
    private val binding by lazy {
        FragmentMyPageBinding.inflate(layoutInflater)
    }

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { uris ->
        if(uris.isNotEmpty() ) {
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
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.imgMyPageAddImg.run {
            setOnClickListener {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }
    }

    private fun loadImage(uri: Uri) {
        Glide.with(requireContext())
            .load(uri)
            .into(binding.imgMyPageMyImg)
    }
}
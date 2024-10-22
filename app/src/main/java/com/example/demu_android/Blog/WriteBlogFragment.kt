package com.example.demu_android.Blog

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demu_android.R
import com.example.demu_android.databinding.FragmentWriteBlogBinding
import com.example.demu_android.recycler.home.WriteBlog.data.Blog
import com.google.android.material.bottomsheet.BottomSheetDialog

class WriteBlogFragment : Fragment(), View.OnClickListener {
    private val blogList: MutableList<Blog> = mutableListOf(
        // Blog(1092L, "", "", "", 12092L, Enum<Tier.GRADE, "">)
    )
    private val binding by lazy {
        FragmentWriteBlogBinding.inflate(layoutInflater)
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

        binding.imgDownArrow.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.img_down_arrow -> {
                showDropDownMenu()
                Log.d("TEST", "함수실행")
            }
        }
    }

    private fun showDropDownMenu() {
        Log.d("TEST", "함수실행2")
        val bottomSheetView = layoutInflater.inflate(R.layout.list_spinner_option, null)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetView)

        binding.imgDownArrow.setOnClickListener {
            Log.d("TEST", "함수실행3")
            bottomSheetDialog.show()
        }
    }
}
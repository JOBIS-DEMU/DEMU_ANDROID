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
    private val bottomSheetView by lazy {
        layoutInflater.inflate(R.layout.list_bottom_sheet_option, null)
    }
    private val bottomSheetDialog by lazy {
        BottomSheetDialog(requireContext())
    }
    private var titleFlag = false
    private var subFlag = false
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
        showDropDownMenu()
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
        bottomSheetDialog.setContentView(bottomSheetView)

        binding.imgDownArrow.setOnClickListener {
            bottomSheetDialog.show()
        }

        bottomSheetView.findViewById<View>(R.id.backend).setOnClickListener {
            bottomSheetDialog.dismiss()
        }
        bottomSheetView.findViewById<View>(R.id.other_major).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "other major"
        }
    }
}
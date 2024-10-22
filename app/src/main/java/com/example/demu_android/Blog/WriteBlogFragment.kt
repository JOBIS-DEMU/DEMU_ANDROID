package com.example.demu_android.Blog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.demu_android.R
import com.example.demu_android.databinding.FragmentWriteBlogBinding
import com.example.demu_android.databinding.ListBottomSheetOptionBinding
import com.example.demu_android.recycler.home.WriteBlog.data.Blog
import com.example.demu_android.utils.isRegexPassword
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
        onTitleListener()
        onSubListener()
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
        //val bottomSheetBinding = ListBottomSheetOptionBinding.inflate(layoutInflater)

        binding.imgDownArrow.setOnClickListener {
            bottomSheetDialog.show()
        }

        bottomSheetView.findViewById<View>(R.id.backend).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "backend"
        }
        bottomSheetView.findViewById<View>(R.id.frontend).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "frontend"
        }
        bottomSheetView.findViewById<View>(R.id.ios).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "iOS"
        }
        bottomSheetView.findViewById<View>(R.id.aos).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "AOS"
        }
        bottomSheetView.findViewById<View>(R.id.ai).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "AI"
        }
        bottomSheetView.findViewById<View>(R.id.design_sub).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "design"
        }
        bottomSheetView.findViewById<View>(R.id.flutter).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "flutter"
        }
        bottomSheetView.findViewById<View>(R.id.full_stack).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "full stack"
        }
        bottomSheetView.findViewById<View>(R.id.game).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "game"
        }
        bottomSheetView.findViewById<View>(R.id.security).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "security"
        }
        bottomSheetView.findViewById<View>(R.id.embedded).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "embedded"
        }
        bottomSheetView.findViewById<View>(R.id.devops).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "devops"
        }
        bottomSheetView.findViewById<View>(R.id.other_major).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "기타 전공"
        }
        bottomSheetView.findViewById<View>(R.id.all_articles).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "전체 글"
        }
    }

    private fun onTitleListener() {
        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    when {
                        s.isEmpty() -> {
                            titleFlag = false
                            if(blogFlagCheck())
                                binding.tvSubmit.setTextColor(resources.getColor(R.color.text_default))
                            else
                                binding.tvSubmit.setTextColor(resources.getColor(R.color.text_gray))

                        }
                        else -> {
                            titleFlag = true
                            if(blogFlagCheck())
                                binding.tvSubmit.setTextColor(resources.getColor(R.color.text_default))
                            else
                                binding.tvSubmit.setTextColor(resources.getColor(R.color.text_gray))
                        }
                    }
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun onSubListener() {
        binding.etSub.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    when {
                        s.isEmpty() -> {
                            subFlag = false
                            if(blogFlagCheck())
                                binding.tvSubmit.setTextColor(resources.getColor(R.color.text_default))
                            else
                                binding.tvSubmit.setTextColor(resources.getColor(R.color.text_gray))

                        }
                        else -> {
                            subFlag = true
                            if(blogFlagCheck())
                                binding.tvSubmit.setTextColor(resources.getColor(R.color.text_default))
                            else
                                binding.tvSubmit.setTextColor(resources.getColor(R.color.text_gray))
                        }
                    }
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun blogFlagCheck(): Boolean {
        return titleFlag && subFlag
    }
}
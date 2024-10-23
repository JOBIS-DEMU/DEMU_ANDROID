package com.example.demu_android.Blog

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import com.example.demu_android.MainActivity
import com.example.demu_android.R
import com.example.demu_android.databinding.FragmentWriteBlogBinding
import com.example.demu_android.databinding.ListBottomSheetOptionBinding
import com.example.demu_android.home.HomeFragment
import com.example.demu_android.recycler.home.WriteBlog.data.Blog
import com.example.demu_android.utils.isRegexPassword
import com.google.android.material.bottomnavigation.BottomNavigationView
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
    private lateinit var listView: ListView

    private var titleFlag = false
    private var subFlag = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.imgDownArrow.setOnClickListener(this)
        binding.tvSubmit.setOnClickListener(this)

        showDropDownMenu()
        onTitleListener()
        onSubListener()
    }

    override fun onClick(v: View?) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()

        when(v?.id) {
            R.id.img_down_arrow -> {
                showDropDownMenu()
                Log.d("TEST", "함수실행")
            }
            R.id.tv_submit -> {
                if (blogFlagCheck()) {
                    transaction?.replace(R.id.containers, HomeFragment())
                    transaction?.commit()
                    // bottom navigation 보여짐
                    // mainActivity에서 hide(), add() 함수 이용하여 구현
                    val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }
            R.id.cv_add_image -> {
                showPhotoPicker()
            }
        }
    }

    private fun showPhotoPicker() {
        val pickMultiMedia = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { urls ->

        }

        pickMultiMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun showDropDownMenu() {
        bottomSheetDialog.setContentView(bottomSheetView)
        //val bottomSheetBinding = ListBottomSheetOptionBinding.inflate(layoutInflater)

        binding.imgDownArrow.setOnClickListener {
            bottomSheetDialog.show()
        }

        val blogList = listOf("backend", "frontend", "iOS", "AOS", "AI", "design", "flutter", "full stack", "game", "security", "embedded", "devops", "기타 전공", "전체 글")
        val adapter = ArrayAdapter(requireContext(), R.layout.list_bottom_sheet_item, blogList)

        listView.findViewById<ListView>(R.id.list_view_bottom_sheet)
        listView.adapter = adapter
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
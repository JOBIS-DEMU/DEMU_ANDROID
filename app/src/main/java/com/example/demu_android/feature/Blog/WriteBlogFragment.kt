package com.example.demu_android.feature.Blog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.demu_android.R
import com.example.demu_android.databinding.FragmentWriteBlogBinding
import com.example.demu_android.databinding.ListBottomSheetItemBinding
import com.example.demu_android.feature.home.HomeFragment
import com.example.demu_android.feature.recycler.home.WriteBlog.data.Blog
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
        layoutInflater.inflate(R.layout.list_bottom_sheet_item, null)
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
        binding.tvCancel.setOnClickListener(this)

        showDropDownMenu()
        onTitleListener()
        onSubListener()
        sendBlogListInfo()
    }

    override fun onClick(v: View?) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val bundle = Bundle()
        bundle.putString("blogTitle", binding.etTitle.text.toString())
        bundle.putString("blogContent", "")
        bundle.putString("blog", "")

        val homeFragment = HomeFragment()
        homeFragment.arguments = bundle

        when(v?.id) {
            R.id.img_down_arrow -> {
                showDropDownMenu()
            }
            R.id.tv_submit -> {
                if (blogFlagCheck()) {
                    transaction?.replace(R.id.containers, homeFragment)
                    transaction?.commit()
                    // bottom navigation 보여짐
                    // mainActivity에서 hide(), add() 함수 이용하여 구현
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }
            R.id.tv_cancel -> {
                transaction?.replace(R.id.containers, HomeFragment())
                transaction?.commit()
                bottomNavigationView.visibility = View.VISIBLE
            }
            R.id.cv_add_image -> {
                showPhotoPicker()
            }
        }
    }

    private fun sendBlogListInfo() {

    }

    private fun showPhotoPicker() {
        val pickMultiMedia = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { urls ->

        }

        pickMultiMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun showDropDownMenu() {
        bottomSheetDialog.setContentView(bottomSheetView)
        val bottomSheetBinding = ListBottomSheetItemBinding.inflate(layoutInflater)

        binding.imgDownArrow.setOnClickListener {
            bottomSheetDialog.show()
        }

        bottomSheetView.findViewById<View>(R.id.backend).setOnClickListener {
            bottomSheetDialog.dismiss()
            binding.tvMajorTitle.text = "backend"
            bottomSheetBinding.tvBottomSheetMajor.text = "backend"
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
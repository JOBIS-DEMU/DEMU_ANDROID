package com.example.demu_android.feature.Blog

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.demu_android.R
import com.example.demu_android.databinding.FragmentWriteBlogBinding
import com.example.demu_android.databinding.ListBottomSheetItemBinding
import com.example.demu_android.feature.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog


class WriteBlogFragment : Fragment(), View.OnClickListener {
    private val binding by lazy {
        FragmentWriteBlogBinding.inflate(layoutInflater)
    }
    private val bottomSheetView by lazy {
        layoutInflater.inflate(R.layout.list_bottom_sheet_item, null)
    }
    private val bottomSheetDialog by lazy {
        BottomSheetDialog(requireContext())
    }
    private val imageItem: MutableList<String> = mutableListOf()

    private val pickMultiMedia = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
        if (imageItem.size >= 5) {
            Toast.makeText(requireContext(), "최대 갯수입니다", Toast.LENGTH_SHORT).show()
        } else if(uris.isNotEmpty() ) {
            Log.d("TEST", "Number of items selected: ${uris.size}")
            binding.tvWriteBlogSelectedImg.text = "${imageItem.size+1}/5"
            val adapter: ArrayAdapter<String> =
                ArrayAdapter<String>(requireContext(), R.layout.photo_image_item, R.id.tv_image, imageItem)
            uris.forEach {
                imageItem.add(it.toString())
            }
            binding.ivImg.adapter = adapter
        } else {
            Log.d("PhotoPicker", "No media selected")
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }
    
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

        binding.cvAddImage.run {
            setOnClickListener {
                pickMultiMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }


        showDropDownMenu()
        onTitleListener()
        onSubListener()
        sendBlogListInfo()
        showPhotoPicker()
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
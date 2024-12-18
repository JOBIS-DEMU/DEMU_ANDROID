package com.example.demu_android.feature

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.demu_android.feature.Blog.WriteBlogFragment
import com.example.demu_android.feature.Mypage.MyPageFragment
import com.example.demu_android.R
import com.example.demu_android.feature.Search.SearchFragment
import com.example.demu_android.databinding.ActivityMainBinding
import com.example.demu_android.feature.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        setBottomNavigationFragment()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.containers, HomeFragment()).commit()
        }
    }

    private fun setBottomNavigationFragment() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.bottom_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.containers, HomeFragment()).commit()
                    true
                }
                R.id.bottom_search -> {
                    supportFragmentManager.beginTransaction().replace(R.id.containers, SearchFragment()).commit()
                    true
                }
                R.id.bottom_blog -> {
                    supportFragmentManager.beginTransaction().replace(R.id.containers, WriteBlogFragment()).commit()
                    binding.bottomNavigation.visibility = View.GONE
                    true
                }
                R.id.bottom_my_page -> {
                    supportFragmentManager.beginTransaction().replace(R.id.containers, MyPageFragment()).commit()
                    true
                }
                else -> false
            }
        }
    }
}
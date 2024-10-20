package com.example.demu_android

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.demu_android.Blog.WrtieBlogFragment
import com.example.demu_android.Mypage.MyPageFragment
import com.example.demu_android.Search.SearchFragment
import com.example.demu_android.databinding.ActivityMainBinding
import com.example.demu_android.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        setBottomNavigationFragment()

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction().replace(R.id.containers, HomeFragment()).commit()
//        }
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
                    supportFragmentManager.beginTransaction().replace(R.id.containers, WrtieBlogFragment()).commit()
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
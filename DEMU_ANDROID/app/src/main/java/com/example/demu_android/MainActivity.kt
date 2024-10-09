    package com.example.demu_android

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.demu_android.Login.LoginActivity
import com.example.demu_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnStart.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val mainToLogin = Intent(this, LoginActivity::class.java)
        when(v) {
            binding.btnStart -> {
                startActivity(mainToLogin)
            }
        }
    }
}
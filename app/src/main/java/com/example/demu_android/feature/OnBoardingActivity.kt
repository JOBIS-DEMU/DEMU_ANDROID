    package com.example.demu_android.feature

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.demu_android.feature.Login.LoginActivity
import com.example.demu_android.databinding.ActivityOnboardingBinding

    class OnBoardingActivity : AppCompatActivity(), View.OnClickListener {
    private val binding: ActivityOnboardingBinding by lazy {
        ActivityOnboardingBinding.inflate(layoutInflater)
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
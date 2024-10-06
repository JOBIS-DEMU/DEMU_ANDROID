
package com.example.demu_android

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        moveToMainActivity()
    }

    private fun moveToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed( {
            startActivity(intent)
        }, 3000)
    }
}
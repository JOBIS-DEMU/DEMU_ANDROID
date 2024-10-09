package com.example.demu_android.Login

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.demu_android.R
import com.example.demu_android.databinding.ActivityLoginBinding
import com.example.demu_android.home.HomeActivity
import com.example.demu_android.utils.isRegexEmail
import com.example.demu_android.utils.isRegexPassword

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    var emailFlag = false
    var passwordFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
        onEmailListener()
        onPasswordListener()
    }

    override fun onClick(v: View?) {
        val loginToHome = Intent(this, HomeActivity::class.java)
        when(v?.id) {
            R.id.btn_login -> {
                if(flagCheck())
                    startActivity(loginToHome)
                else
                    Toast.makeText(this, "정확히 값을 입력해주세요", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun onEmailListener() {
        binding.tieEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    when {
                        s.isEmpty() -> {
                            binding.tilEmail.error = "이메일을 입력해주세요"
                        }
                        !isRegexEmail(s.toString()) -> {
                            binding.tilEmail.error = "유효하지 않은 이메일 입니다."
                        }
                        else -> {
                            binding.tilEmail.error = null
                            binding.tilEmail.boxStrokeColor = getResources().getColor(R.color.main)
                            emailFlag = true
                            if (flagCheck())
                                binding.btnLogin.setBackgroundDrawable(ContextCompat.getDrawable(baseContext,R.drawable.bg_button))
                            else
                                binding.btnLogin.setBackgroundDrawable(ContextCompat.getDrawable(baseContext,R.drawable.button_color_nocheck))
                        }
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun onPasswordListener() {
        binding.tiePassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s != null) {
                    when {
                        s.isEmpty() -> {
                            binding.tilPassword.error = "비밀번호를 입력해주세요"
                        }
                        !isRegexPassword(s.toString()) -> {
                            binding.tilPassword.error = "비밀번호 형식이 맞지 않습니다!"
                        }
                        else -> {
                            binding.tilPassword.error = null
                            passwordFlag = true
                            binding.tilEmail.boxStrokeColor = getResources().getColor(R.color.main)
                            if (flagCheck())
                                binding.btnLogin.setBackgroundDrawable(ContextCompat.getDrawable(baseContext,R.drawable.bg_button))
                            else
                                binding.btnLogin.setBackgroundDrawable(ContextCompat.getDrawable(baseContext,R.drawable.button_color_nocheck))
                        }
                    }
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun flagCheck(): Boolean {
        return emailFlag && passwordFlag
    }
}

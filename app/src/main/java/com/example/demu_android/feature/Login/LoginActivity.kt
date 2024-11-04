package com.example.demu_android.feature.Login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.demu_android.R
import com.example.demu_android.data.api.ApiProvider
import com.example.demu_android.data.request.auth.LoginRequest
import com.example.demu_android.data.request.auth.SignUpRequest
import com.example.demu_android.data.response.auth.LoginResponse
import com.example.demu_android.data.response.auth.SignUpResponse
import com.example.demu_android.databinding.ActivityLoginBinding
import com.example.demu_android.feature.MainActivity
import com.example.demu_android.feature.signUp.SignUpActivity
import com.example.demu_android.feature.utils.isRegexEmail
import com.example.demu_android.feature.utils.isRegexPassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    var emailFlag = false
    var passwordFlag = false

    private val retrofit = ApiProvider.getAuthApi()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
        binding.tvBottomSignUp.setOnClickListener(this)

        onEmailListener()
        onPasswordListener()
    }

    override fun onClick(v: View?) {
        val loginToSignUp = Intent(this, SignUpActivity::class.java)
        val loginToHome = Intent(this, MainActivity::class.java)

        val email = binding.tieEmail.text.toString()
        val password = binding.tiePassword.text.toString()

        when(v?.id) {
            R.id.btn_login -> {
                if (flagCheck()) {
                    startActivity(loginToHome)
                    connectSignUpToServer(email, password)
                } else
                    Toast.makeText(this, "정확히 값을 입력해주세요", Toast.LENGTH_LONG).show()
            }
            R.id.tv_bottom_sign_up -> {
                startActivity(loginToSignUp)
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
                            val resource = if(flagCheck()) R.drawable.bg_button else R.drawable.button_color_nocheck
                            binding.btnLogin.setBackgroundDrawable(ContextCompat.getDrawable(baseContext,resource))
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

    private fun connectSignUpToServer(email: String, password: String) {
        val loginToHome = Intent(this, MainActivity::class.java)

        retrofit.login(
            LoginRequest(
                accountId = email,
                password = password
            )
        ).enqueue(object :  Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(loginToHome)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("TEST", t.message.toString())
            }
        })
    }
}

package com.example.demu_android.feature.signUp

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
import com.example.demu_android.feature.Login.LoginActivity
import com.example.demu_android.R
import com.example.demu_android.data.api.ApiProvider
import com.example.demu_android.data.request.auth.SignUpRequest
import com.example.demu_android.data.response.auth.SignUpResponse
import com.example.demu_android.databinding.ActivitySignUpBinding
import com.example.demu_android.feature.utils.isRegexEmail
import com.example.demu_android.feature.utils.isRegexNickName
import com.example.demu_android.feature.utils.isRegexPassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    private val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    var emailFlag = false
    var nickNameFlag = false
    var passwordFlag = false
    var passwordCheckFlag = false

    private val retrofit = ApiProvider.getAuthApi()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener(this)
        binding.tvBottomLogin.setOnClickListener(this)

        onEmailListener()
        onNickNameListener()
        onPasswordListener()
        onPasswordCheckListener()
    }

    override fun onClick(v: View?) {
        val signUpToLogin = Intent(this, LoginActivity::class.java)

        val email = binding.tieEmail.toString()
        val nickName = binding.tieNickName.toString()
        val password = binding.tiePassword.toString()

        when(v?.id) {
            R.id.btn_sign_up -> {
                if(flagCheck()) {
                    connectSignUpToServer(email, nickName, password)

                } else
                    Toast.makeText(this, "정확히 값을 입력해주세요", Toast.LENGTH_LONG).show()
            }
            R.id.tv_bottom_login -> {
                startActivity(signUpToLogin)
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
                                binding.btnSignUp.setBackgroundDrawable(ContextCompat.getDrawable(baseContext,R.drawable.bg_button))
                            else
                                binding.btnSignUp.setBackgroundDrawable(ContextCompat.getDrawable(baseContext,R.drawable.button_color_nocheck))
                        }
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun onNickNameListener() {
        binding.tieNickName.addTextChangedListener(object  : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    when {
                        s.isEmpty() -> {
                            binding.tilNickName.error = "닉네임을 입력해주세요"
                        }
                        // 닉네임이 같을 경우
                        !isRegexNickName(s.toString()) -> {
                            binding.tilNickName.error = "닉네임 형식이 맞지 않습니다"
                        }
                        else -> {
                            binding.tilNickName.error = null
                            binding.tilNickName.boxStrokeColor = getResources().getColor(R.color.main)
                            nickNameFlag = true
                            if (flagCheck())
                                binding.btnSignUp.setBackgroundDrawable(ContextCompat.getDrawable(baseContext,R.drawable.bg_button))
                            else
                                binding.btnSignUp.setBackgroundDrawable(ContextCompat.getDrawable(baseContext,R.drawable.button_color_nocheck))
                        }
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun onPasswordListener() {
        binding.tiePassword.addTextChangedListener(object : TextWatcher {
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
                            binding.tilPassword.boxStrokeColor = getResources().getColor(R.color.main)
                            // 이거이름 맞춰서 수정
                            val resource = if(flagCheck()) R.drawable.bg_button else R.drawable.button_color_nocheck
                            binding.btnSignUp.setBackgroundDrawable(ContextCompat.getDrawable(baseContext,resource))
                        }
                    }
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun onPasswordCheckListener() {
        binding.tiePasswordCheck.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    when {
                        s.isEmpty() -> {
                            binding.tilPasswordCheck.error = "비밀번호를 입력해주세요"
                        }
                        !isRegexPassword(s.toString()) -> {
                            binding.tilPasswordCheck.error = "비밀번호 형식이 맞지 않습니다!"
                        }
                        binding.tiePassword.text.toString() != s.toString() -> {
                            binding.tilPasswordCheck.error = "비밀번호가 일치하지 않습니다"
                        }
                        else -> {
                            binding.tilPasswordCheck.error = null
                            passwordCheckFlag = true
                            binding.tilPasswordCheck.boxStrokeColor = getResources().getColor(R.color.main)
                            // 이거이름 맞춰서 수정
                            val resource = if(flagCheck()) R.drawable.bg_button else R.drawable.button_color_nocheck
                            binding.btnSignUp.setBackgroundDrawable(ContextCompat.getDrawable(baseContext,resource))
                        }
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun flagCheck(): Boolean {
        return emailFlag && nickNameFlag && passwordFlag && passwordCheckFlag
    }

    private fun connectSignUpToServer(email: String, nickName: String, password: String) {
        val signUpToLogin = Intent(this, LoginActivity::class.java)

        retrofit.signUp(
            SignUpRequest(
                accountId = email,
                nickname = nickName,
                password = password
            )
        ).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                when(response.code()) {
                    200 -> {
                        startActivity(signUpToLogin)
                    }
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.d("TEST", t.message.toString())
                Log.d("TEST", t.cause.toString())
            }
        })
    }
}
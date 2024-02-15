package com.example.appstudy.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.appstudy.MainActivity
import com.example.appstudy.R
import com.example.appstudy.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.loginBtn.setOnClickListener {

            var isGoToLogin = true

            val userEmail = binding.userEmail.text.toString()
            val userPassword = binding.userPassword.text.toString()

            if(userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "아이디 또는 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show()
                isGoToLogin = false
            }

            if(isGoToLogin) {
                auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        // 로그인 후 뒤로가기 버튼을 누르면 로그인 페이지가 아닌 앱 종료가 된다.

                    } else {
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}
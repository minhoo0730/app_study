package com.example.appstudy.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.appstudy.MainActivity
import com.example.appstudy.R
import com.example.appstudy.databinding.ActivityJoinBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var binding : ActivityJoinBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)
        binding.joinBtn.setOnClickListener {

            var isGoToJoin = true

            val userEmail = binding.userEmail.text.toString()
            val userPasssword = binding.userPassword.text.toString()
            val userPasswordCheck = binding.userPasswordCheck.text.toString()

            // 3개의 폼에 값이 있는지 체크
            if(userEmail.isEmpty()){
                Toast.makeText(this,"이메일을 입력하세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }
            if(userPasssword.isEmpty() || userPasswordCheck.isEmpty()){
                Toast.makeText(this,"비밀번호를 입력하세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }
            if(!userPasssword.equals(userPasswordCheck)){
                Toast.makeText(this,"입력하신 비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }
            if(userPasssword.length < 8){
                Toast.makeText(this,"비밀번호를 8자리 이상 입력하세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false
            }

            if(isGoToJoin) {
                auth.createUserWithEmailAndPassword(userEmail, userPasssword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"회원가입 성공.", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    } else {
                        Toast.makeText(this,"회원가입 실패", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}
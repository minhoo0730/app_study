package com.example.appstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.appstudy.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val joinBtn = findViewById<Button>(R.id.joinBtn)
        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val logoutBtn = findViewById<Button>(R.id.logoutBtn)
        joinBtn.setOnClickListener{
            // 로그인 방법 1
//            val email = findViewById<EditText>(R.id.emailArea)
//            val password = findViewById<EditText>(R.id.passwordArea)

            // 로그인 방법 2
            val email = binding.emailArea
            val password = binding.passwordArea

            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this,"회원가입 실패",Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.loginBtn.setOnClickListener{
            // 로그인 방법 2
            val email = binding.emailArea
            val password = binding.passwordArea

            auth.signInWithEmailAndPassword(
                email.text.toString(),
                password.text.toString()
            ).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, BoardListActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.logoutBtn.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, auth.currentUser?.uid.toString(),Toast.LENGTH_SHORT).show()
        }
    }
}
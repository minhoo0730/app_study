package com.example.appstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BoardWhiteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_white)

        val writeBtn:Button = findViewById(R.id.whiteUploadBtn)
        writeBtn.setOnClickListener {
            val whiteText:EditText = findViewById(R.id.whiteTextArea)

            val database = Firebase.database
            val myRef = database.getReference("message")

            myRef.push().setValue(
                whiteText.text.toString()
            )
        }
    }
}
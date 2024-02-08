package com.example.appstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BoardListActivity : AppCompatActivity() {

    lateinit var LVAdapter : ListViewAdapter
    val list = mutableListOf<Model>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_list)

        val whiteBtn = findViewById<Button>(R.id.writeBtn)

        whiteBtn.setOnClickListener {
            val intent = Intent(this, BoardWhiteActivity::class.java)
            startActivity(intent)
        }

        LVAdapter = ListViewAdapter(list)

        val lv: ListView = findViewById(R.id.lv)
        lv.adapter = LVAdapter
        getData()
    }
    fun getData(){
        val database = Firebase.database
        val myRef = database.getReference("message")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
//            val post = dataSnapshot.getValue<Post>()
                for(dataModel in dataSnapshot.children){
                    val item = dataModel.getValue(Model::class.java)
                    Log.d("BoardListActivity", item.toString())
                    list.add(item!!)
                }
                // ...
                LVAdapter.notifyDataSetChanged()
            }


            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("BoardListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)
    }
}
package com.example.whissage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //run()
        val b = findViewById<Button>(R.id.skipButton)
        b.setOnClickListener {
            if (userEnter.text.isNotEmpty()) {
                App.user = userEnter.text.toString()
                startActivity(Intent(this, ChatActivity::class.java))
            } else {
                Toast.makeText(
                    applicationContext,
                    "Username should not be empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
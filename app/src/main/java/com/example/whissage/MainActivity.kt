package com.example.whissage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.neovisionaries.ws.client.WebSocket
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //run()
        val b = findViewById<Button>(R.id.skipButton)
        b.setOnClickListener {
            if (userEnter.text.isNotEmpty()) {
                val user = userEnter.text.toString()
                startActivity(Intent(this, ChatActivity::class.java))
                App.user = user
                startActivity(Intent(this@MainActivity, ChatActivity::class.java))
            } else {
                Toast.makeText(applicationContext,"Username should not be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    fun run() {
//        var client = OkHttpClient().newBuilder()
//            .readTimeout(0, TimeUnit.MILLISECONDS)
//            .build()
//        var request = Request.Builder().url("ws://localhost:8081").build()
//        webSocket = client.newWebSocket(request, this)
//    }
//
//    fun onOpen(webSocket: okhttp3.WebSocket, response: Response) {
//        super.onOpen(webSocket, response)
//        Log.e("TAG", "onOpen")
//    }
//
//    fun onMessage(webSocket: okhttp3.WebSocket, text: String) {
//        Log.e("TAG", text)
//        try {
//            val obj = JSONObject(text)
//            Log.e("My App", obj.toString())
//        } catch (tx: Throwable) {
//            Log.e(
//                "My App",
//                "Could not parse malformed JSON: \"$text\""
//            )
//        }
}

package com.example.whissage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.neovisionaries.ws.client.WebSocket

class MainActivity : AppCompatActivity() {

    lateinit var webSocket: WebSocket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //run()
        val b = findViewById<Button>(R.id.skipButton)
        b.setOnClickListener {
            val i = Intent(this, ChatActivity::class.java)
            startActivity(i)
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

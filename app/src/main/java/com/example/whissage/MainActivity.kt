package com.example.whissage


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.neovisionaries.ws.client.WebSocket
import okhttp3.*
import org.json.JSONObject
import java.security.Policy
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var webSocket: WebSocket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //        run()
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

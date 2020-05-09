package com.example.whissage

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketFactory
import com.neovisionaries.ws.client.WebSocketFrame

class ChatActivity : AppCompatActivity() {

    lateinit var webSocket: WebSocket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)


        fun testWSS() {
            // Create a WebSocket with a socket connection timeout value.
            val ws = WebSocketFactory().createSocket("wss://echo.websocket.org", 5000)

            // Register a listener to receive WebSocket events.
            ws.addListener(object : WebSocketAdapter() {
                override fun onTextMessage(websocket: WebSocket?, text: String?) {
                    super.onTextMessage(websocket, text)
                    Log.v("WSS", "text")
                }

                override fun onCloseFrame(websocket: WebSocket?, frame: WebSocketFrame?) {
                    super.onCloseFrame(websocket, frame)
                    Log.v("WSS", "closing socket")
                }
            })
            ws.connect()
            ws.sendText("hello !")
            ws.disconnect()
        }
        Thread{testWSS()}

    }
}

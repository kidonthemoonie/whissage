package com.example.whissage

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketFactory
import com.neovisionaries.ws.client.WebSocketFrame
import kotlin.concurrent.thread

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        thread() {
            // Create a WebSocket with a socket connection timeout value.
            val ws = WebSocketFactory().createSocket("ws://185.185.70.69/ws/", 5000)

            // Register a listener to receive WebSocket events.
            ws.addListener(object : WebSocketAdapter() {
                override fun onTextMessage(websocket: WebSocket?, text: String?) {
                    super.onTextMessage(websocket, text)
                    Log.e("WS", "text")
                }

                override fun onCloseFrame(websocket: WebSocket?, frame: WebSocketFrame?) {
                    super.onCloseFrame(websocket, frame)
                    Log.e("WS1", "closing socket")
                }
            })
            ws.connect()
            ws.sendText("hello from ANDROID !")
            ws.disconnect()
        }
    }
}

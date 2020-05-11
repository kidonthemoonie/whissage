package com.example.whissage

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketFactory
import com.neovisionaries.ws.client.WebSocketFrame
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.*
import kotlin.concurrent.thread

class ChatActivity : AppCompatActivity() {

    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val sendButton = findViewById<Button>(R.id.send)
        val textMessage = findViewById<EditText>(R.id.enter_mess)

        messageList.layoutManager = LinearLayoutManager(this)
        adapter = MessageAdapter(this)
        messageList.adapter = adapter

        thread() {
            val ws = WebSocketFactory().createSocket("ws://185.185.70.69/ws/", 5000)

            // Register a listener to receive WebSocket events.
            ws.addListener(object : WebSocketAdapter() {
                override fun onTextMessage(websocket: WebSocket, text: String) {
                    super.onTextMessage(websocket, text)
                    Log.e("ws_s", text.toString())
                    if (text.isNotEmpty()) {
                        runOnUiThread {
                            adapter.addMessage(
                                Message(
                                    App.user,
                                    text,
                                    Calendar.getInstance().timeInMillis
                                )
                            )
                            messageList.scrollToPosition(adapter.itemCount - 1);
                        }
                    }

                }

                override fun onCloseFrame(websocket: WebSocket?, frame: WebSocketFrame?) {
                    super.onCloseFrame(websocket, frame)
                    Log.e("ws_c", "closing socket")
                }
            })

            ws.connect()

            sendButton.setOnClickListener() {
                ws.sendText(textMessage.text.toString())
                textMessage.text.clear()
                textMessage.hint = " "
            }
        }
    }

}

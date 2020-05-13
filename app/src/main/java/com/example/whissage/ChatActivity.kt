package com.example.whissage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.neovisionaries.ws.client.WebSocket
import com.neovisionaries.ws.client.WebSocketAdapter
import com.neovisionaries.ws.client.WebSocketFactory
import com.neovisionaries.ws.client.WebSocketFrame
import kotlinx.android.synthetic.main.activity_chat.*
import org.json.JSONObject
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
                    Log.e("get message: ", text)
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
                val jsonObject: JSONObject =
                val message = Message(
                    jsonObject["user"].toString(),
                    jsonObject["message"].toString(),
                    jsonObject["time"].toString().toLong()
                )
                ws.sendText(message.toString())
                textMessage.text.clear()
                textMessage.hint = " "
                val inputManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(
                    currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
    }
    override fun onBackPressed(){
        super.onBackPressed()
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

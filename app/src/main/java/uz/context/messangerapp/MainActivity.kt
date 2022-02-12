package uz.context.messangerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.context.messangerapp.adapter.ChatAdapter
import uz.context.messangerapp.adapter.RoomAdapter
import uz.context.messangerapp.model.Chat
import uz.context.messangerapp.model.Message
import uz.context.messangerapp.model.Room

class MainActivity : AppCompatActivity() {
    private val stories: ArrayList<Room> = ArrayList()
    private val chats: ArrayList<Chat> = ArrayList()
    private lateinit var recyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerview_main)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        chatAdapter = ChatAdapter(this, getAllChats())
        recyclerView.adapter = chatAdapter
    }

    private fun getAllChats(): ArrayList<Chat> {
        for (i in 0..30) {
            if (i % 2 == 0)
                stories.add(Room(R.drawable.kotlin_img, "Kotlin", true))
            else
                stories.add(Room(R.drawable.java_logo, "Java", false))
        }

        chats.add(Chat(stories))

        for (i in 0..30) {
            if (i % 2 == 0)
                chats.add(Chat(Message(R.drawable.kotlin_img, "Kotlin", false)))
            else
                chats.add(Chat(Message(R.drawable.java_logo, "Java", true)))
        }

        return chats
    }
}
package uz.context.messangerapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import uz.context.messangerapp.R
import uz.context.messangerapp.model.Chat
import uz.context.messangerapp.model.Room

class ChatAdapter(val context: Context, private val items: ArrayList<Chat>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private val TYPE_ITEM_ROOM = 0
        private val TYPE_ITEM_MESSAGE = 1
    }

    override fun getItemViewType(position: Int): Int {
        val feed = items[position]
        if (feed.rooms.size > 0)
            return TYPE_ITEM_ROOM
        return TYPE_ITEM_MESSAGE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM_ROOM) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_chat_room, parent, false)
            return RoomViewHolder(context, view)
        }
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_chat_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = items[position]

        if (holder is RoomViewHolder) {
            val recyclerView = holder.recyclerView
            refreshAdapter(message.rooms, recyclerView)
        }
        if (holder is MessageViewHolder) {
            holder.apply {
                ivProfile.setImageResource(message.message!!.profile)
                tvFullName.text = message.message!!.fullName
                if (message.message!!.isOnline) {
                    isOnline.apply {
                        visibility = View.VISIBLE
                        setBackgroundResource(R.drawable.is_online_shape)
                    }

                } else {
                    isOnline.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class RoomViewHolder(context: Context, view: View) : RecyclerView.ViewHolder(view) {
        var recyclerView: RecyclerView

        init {
            recyclerView = view.findViewById(R.id.recyclerView)
            val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = manager
        }
    }

    class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivProfile: ShapeableImageView
        var tvFullName: TextView
        var isOnline: View

        init {
            ivProfile = view.findViewById(R.id.iv_profile_item)
            tvFullName = view.findViewById(R.id.tv_full_name_item)
            isOnline = view.findViewById(R.id.is_online)
        }
    }

    private fun refreshAdapter(rooms: ArrayList<Room>, recyclerView: RecyclerView) {
        val adapter = RoomAdapter(context, rooms)
        recyclerView.adapter = adapter
    }
}
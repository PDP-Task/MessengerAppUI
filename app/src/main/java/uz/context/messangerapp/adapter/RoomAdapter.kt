package uz.context.messangerapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import uz.context.messangerapp.R
import uz.context.messangerapp.model.Room

class RoomAdapter(context: Context, val items: ArrayList<Room>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) 1
        else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 1) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_create_room, parent, false)
            return CreateViewHolder(view)
        }
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_room_view, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val room = items[position]

        if (holder is RoomViewHolder) {
            holder.apply {
                ivProfile.setImageResource(room.profile)
                tvFullName.text = room.fullName

                if (room.isOnline) {
                    isOnline.setBackgroundResource(R.drawable.is_online_shape)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProfile: ShapeableImageView = view.findViewById(R.id.iv_profile_room)
        val tvFullName: TextView = view.findViewById(R.id.tv_full_name_room)
        val isOnline: View = view.findViewById(R.id.is_online_room)
    }
    class CreateViewHolder(view: View): RecyclerView.ViewHolder(view) {}
}
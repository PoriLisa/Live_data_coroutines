package com.example.livedatacoroutines.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.livedatacoroutines.R
import com.example.livedatacoroutines.model.User
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private val users: ArrayList<User>,
   private  var itemOnClick: ItemOnClick?
) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
   // lateinit var itemOnClick: ItemOnClick

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User, itemOnClick: ItemOnClick) = with(itemView) {
            itemView.apply {
                textViewUserName.text = user.name
                textViewUserEmail.text = user.email
                Glide.with(imageViewAvatar.context)
                    .load(user.avatar)
                    .into(imageViewAvatar)
            }

            setOnClickListener {
                itemOnClick.itemOnClick(user,itemView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        itemOnClick?.let { holder.bind(users[position], it) }
    }

    fun addUsers(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
   /* fun setOnClick(listner:ItemOnClick){
        this.itemOnClick = listner
    }*/
    public interface ItemOnClick {
        fun itemOnClick(users: User, itemView: View)

    }
}
package com.example.mvi_arch_beginner.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvi_arch_beginner.R
import com.example.mvi_arch_beginner.data.model.User
import kotlinx.android.synthetic.main.item_layout.view.imageViewAvatar
import kotlinx.android.synthetic.main.item_layout.view.textViewUserEmail
import kotlinx.android.synthetic.main.item_layout.view.textViewUserName

class MainAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.textViewUserName.text = user.name
            itemView.textViewUserEmail.text = user.email
            Glide.with(itemView.imageViewAvatar.context)
                .load(user.avatar)
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
    )

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(users[position])

    override fun getItemCount(): Int = users.size

    fun addData(list: List<User>) {
        users.addAll(list)
    }
}

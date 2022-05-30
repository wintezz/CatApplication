package com.example.catapplication.domain.util

import androidx.recyclerview.widget.DiffUtil
import com.example.catapplication.data.remote.Cat

class DiffUtilCallBack : DiffUtil.ItemCallback<Cat>() {
    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.imageUrl == newItem.imageUrl
    }
}
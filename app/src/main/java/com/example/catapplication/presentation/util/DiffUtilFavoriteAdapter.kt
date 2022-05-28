package com.example.catapplication.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.example.catapplication.presentation.db.entityes.FavoriteItem

class DiffUtilFavoriteAdapter : DiffUtil.ItemCallback<FavoriteItem>() {
    override fun areItemsTheSame(
        oldItem: FavoriteItem,
        newItem: FavoriteItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: FavoriteItem,
        newItem: FavoriteItem
    ): Boolean {
        return oldItem == newItem
    }
}

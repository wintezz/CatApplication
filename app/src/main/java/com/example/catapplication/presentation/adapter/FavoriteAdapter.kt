package com.example.catapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.catApllication.R
import com.example.catApllication.databinding.CatItemBinding
import com.example.catapplication.data.db.entityes.FavoriteItem
import com.example.catapplication.presentation.util.DiffUtilFavoriteAdapter

class FavoriteAdapter :
    ListAdapter<FavoriteItem, FavoriteAdapter.ItemHolder>(DiffUtilFavoriteAdapter()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CatItemBinding.bind(view)
        fun setData(favoriteItem: FavoriteItem) {
            binding.imageView.load(favoriteItem.image)
        }

        companion object {
            fun create(parent: ViewGroup): ItemHolder {
                return ItemHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.cat_item, parent, false)
                )
            }
        }
    }
}
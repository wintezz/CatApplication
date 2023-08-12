package com.example.catapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.catApllication.databinding.CatItemBinding
import com.example.catapplication.data.db.entity.FavoriteItem

class FavoriteAdapter :
    ListAdapter<FavoriteItem, FavoriteAdapter.FavoriteViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            CatItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FavoriteViewHolder(private val binding: CatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FavoriteItem) {
            binding.imageView.load(item.image)
        }
    }

    companion object {

        private val diffCallBack = object : DiffUtil.ItemCallback<FavoriteItem>() {

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
    }
}
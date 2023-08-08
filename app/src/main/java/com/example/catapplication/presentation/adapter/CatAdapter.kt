package com.example.catapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.catApllication.databinding.CatItemBinding
import com.example.catapplication.presentation.model.CatUiModel

class CatAdapter(
    private val clickListener: (CatUiModel) -> Unit,
    private val favoriteClickListener: (CatUiModel) -> Unit,
) : PagingDataAdapter<CatUiModel, CatAdapter.CatViewHolder>(diffCallBack) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CatViewHolder {
        return CatViewHolder(
            CatItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }

    inner class CatViewHolder(private val binding: CatItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CatUiModel) {
            binding.imageView.load(data.imageUrl)

            itemView.setOnClickListener {
                clickListener.invoke(data)
            }

            binding.buttonFavorite.setOnClickListener {
                favoriteClickListener.invoke(data)
            }
        }
    }

    companion object {

        private val diffCallBack = object : DiffUtil.ItemCallback<CatUiModel>() {

            override fun areItemsTheSame(oldItem: CatUiModel, newItem: CatUiModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CatUiModel, newItem: CatUiModel): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.imageUrl == newItem.imageUrl
            }
        }
    }
}
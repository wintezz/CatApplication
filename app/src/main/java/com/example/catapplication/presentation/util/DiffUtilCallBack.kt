package com.example.catapplication.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.example.catapplication.domain.models.CatUiModel

class DiffUtilCallBack : DiffUtil.ItemCallback<CatUiModel>() {
    override fun areItemsTheSame(oldItem: CatUiModel, newItem: CatUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CatUiModel, newItem: CatUiModel): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.imageUrl == newItem.imageUrl
    }
}
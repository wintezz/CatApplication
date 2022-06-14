package com.example.catapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.catapplication.R
import com.example.catapplication.domain.models.CatUiModel
import com.example.catapplication.presentation.util.DiffUtilCallBack

class CatAdapter(
    private val clickListener: (CatUiModel) -> Unit,
    private val favoriteClickListener: (CatUiModel) -> Unit,
) : PagingDataAdapter<CatUiModel, CatAdapter.CatViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cat_item, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }

    inner class CatViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        private val imageView = view.findViewById<ImageView>(R.id.imageView)
        private var buttonFavorite = view.findViewById<ImageButton>(R.id.buttonFavorite)

        fun bind(data: CatUiModel) {
            imageView.load(data.imageUrl)
            itemView.setOnClickListener { clickListener.invoke(data) }
            buttonFavorite.setOnClickListener { favoriteClickListener.invoke(data) }
        }
    }
}



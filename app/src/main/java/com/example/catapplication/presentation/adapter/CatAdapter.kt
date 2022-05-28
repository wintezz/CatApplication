package com.example.catapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.catapplication.R
import com.example.catapplication.presentation.interfaces.Navigator
import com.example.catapplication.presentation.util.DiffUtilCallBack
import com.example.catapplication.repository.Cat

class CatAdapter(
    private val navigator: Navigator,
) : PagingDataAdapter<Cat, CatAdapter.CatViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cat_item, parent, false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bind(getItem(position)!!)

        holder.itemView.setOnClickListener {
            navigator.openCatInfoFragment(getItem(position)?.imageUrl!!, getItem(position)?.id!!)
        }

        /*holder.buttonFavorite.setOnClickListener{
          navigator.openSecondActivity(getItem(position)?.imageUrl!!, getItem(position)?.id!!)
        }*/
    }

    class CatViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        private val imageView = view.findViewById<ImageView>(R.id.imageView)
        // var buttonFavorite = view.findViewById<ImageButton>(R.id.buttonFavorite)

        fun bind(data: Cat) {
            imageView.load(data.imageUrl)
        }
    }
}



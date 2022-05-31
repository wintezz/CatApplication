package com.example.catapplication.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catapplication.MainApp
import com.example.catapplication.databinding.ActivitySecondBinding
import com.example.catapplication.presentation.model.CatViewModel
import com.example.catapplication.presentation.adapter.FavoriteAdapter

class SecondActivity : AppCompatActivity() {

    private lateinit var adapter: FavoriteAdapter
    private lateinit var binding: ActivitySecondBinding

    private val catViewModel: CatViewModel by viewModels {
        CatViewModel.MainViewModelFactory((applicationContext as MainApp).dataBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        observer()
    }

    private fun initRecyclerView() = with(binding) {
        recyclerFavorite.layoutManager = LinearLayoutManager(this@SecondActivity)
        adapter = FavoriteAdapter()
        recyclerFavorite.adapter = adapter
    }

    private fun observer() {
        catViewModel.getFavoriteItem.observe(this) {
            adapter.submitList(it)
        }
    }
}
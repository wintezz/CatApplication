package com.example.catapplication.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catApllication.databinding.ActivitySecondBinding
import com.example.catapplication.presentation.adapter.FavoriteAdapter
import com.example.catapplication.presentation.viewmodel.CatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private val favoriteAdapter = FavoriteAdapter()

    private var _binding: ActivitySecondBinding? = null
    private val binding
        get() = _binding ?: throw Throwable("SecondActivity binding is not initialized")

    private val catViewModel: CatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        observer()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun initRecyclerView() {
        with(binding) {
            recyclerFavorite.layoutManager = LinearLayoutManager(applicationContext)
            recyclerFavorite.adapter = favoriteAdapter
        }
    }

    private fun observer() {
        catViewModel.getFavoriteItem.observe(this) {
            favoriteAdapter.submitList(it)
        }
    }
}
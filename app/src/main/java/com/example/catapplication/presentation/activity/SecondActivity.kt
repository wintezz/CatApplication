package com.example.catapplication.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catApllication.databinding.ActivitySecondBinding
import com.example.catapplication.CatApplication
import com.example.catapplication.presentation.adapter.FavoriteAdapter
import com.example.catapplication.presentation.viewmodel.CatViewModel

class SecondActivity : AppCompatActivity() {

    private lateinit var adapter: FavoriteAdapter
    private var _binding: ActivitySecondBinding? = null
    private val binding
        get() = _binding ?: throw Throwable("SecondActivity binding is not initialized")

    private val catViewModel: CatViewModel by viewModels {
        CatViewModel.MainViewModelFactory(CatApplication.dataBase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySecondBinding.inflate(layoutInflater)
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

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
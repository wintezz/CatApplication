package com.example.catapplication.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catapplication.MainApp
import com.example.catapplication.R
import com.example.catapplication.databinding.ActivitySecondBinding
import com.example.catapplication.domain.CatViewModel
import com.example.catapplication.presentation.adapter.FavoriteAdapter
import com.example.catapplication.repository.Cat

class SecondActivity : AppCompatActivity() {

    private lateinit var adapter: FavoriteAdapter
    private lateinit var binding: ActivitySecondBinding

    private val catViewModel: CatViewModel by viewModels {
        CatViewModel.MainViewModelFactory((applicationContext as MainApp).database)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRcView()
        observer()
    }

    private fun initRcView() = with(binding) {
        recyclerView2.layoutManager = LinearLayoutManager(this@SecondActivity)
        adapter = FavoriteAdapter()
        recyclerView2.adapter = adapter
    }

    private fun observer() {
        catViewModel.getFavoriteItem.observe(this) {
            adapter.submitList(it)
        }
    }
}
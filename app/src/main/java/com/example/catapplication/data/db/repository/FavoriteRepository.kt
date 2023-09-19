package com.example.catapplication.data.db.repository

import com.example.catapplication.data.db.entity.FavoriteItem
import com.example.catapplication.presentation.model.CatModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getAllItems(): Flow<List<FavoriteItem>>

    fun addItem(catModel: CatModel)

    fun deleteItem(item: FavoriteItem)
}
package com.example.catapplication.data.db.repository

import com.example.catapplication.data.db.entity.FavoriteItem
import com.example.catapplication.presentation.model.CatUiModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getAllItems(): Flow<List<FavoriteItem>>

    fun addItem(catUiModel: CatUiModel)

    fun deleteItem(item: FavoriteItem)
}
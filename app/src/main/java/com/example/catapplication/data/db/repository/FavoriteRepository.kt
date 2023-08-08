package com.example.catapplication.data.db.repository

import com.example.catapplication.CatApplication
import com.example.catapplication.data.db.entityes.FavoriteItem
import com.example.catapplication.presentation.model.CatUiModel
import kotlinx.coroutines.flow.Flow

object FavoriteRepository {

    private val dao = CatApplication.dataBase.getDao()

    fun addFavorite(catUiModel: CatUiModel) {
        dao.insertFavorite(
            FavoriteItem(
                id = null,
                image = catUiModel.imageUrl,
                catId = catUiModel.id
            )
        )
    }

    fun removeFavorite(item: FavoriteItem) {
        dao.deleteFavorite(item)
    }

    fun getAll(): Flow<List<FavoriteItem>> {
        return dao.getAllFavoriteItems()
    }
}
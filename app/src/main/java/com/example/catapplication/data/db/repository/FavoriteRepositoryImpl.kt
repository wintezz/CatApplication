package com.example.catapplication.data.db.repository

import com.example.catapplication.data.db.Dao
import com.example.catapplication.data.db.entity.FavoriteItem
import com.example.catapplication.presentation.model.CatModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: Dao
) : FavoriteRepository {

    override fun getAllItems(): Flow<List<FavoriteItem>> {
        return dao.getAllItems()
    }

    override fun addItem(catModel: CatModel) {
        dao.addItem(
            FavoriteItem(
                id = null,
                image = catModel.imageUrl,
                catId = catModel.id
            )
        )
    }

    override fun deleteItem(item: FavoriteItem) {
        dao.deleteItem(item)
    }
}
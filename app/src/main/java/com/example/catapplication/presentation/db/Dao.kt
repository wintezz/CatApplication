package com.example.catapplication.presentation.db

import androidx.room.*
import androidx.room.Dao
import com.example.catapplication.presentation.db.entityes.FavoriteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM favorite_list")
    fun getAllFavoriteItems():Flow<List<FavoriteItem>>

    @Insert
    fun insertFavorite(favoriteItem: FavoriteItem)

    @Delete
    fun deleteFavorite(favoriteItem: FavoriteItem)
}
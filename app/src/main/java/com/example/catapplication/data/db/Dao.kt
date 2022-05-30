package com.example.catapplication.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.catapplication.data.db.entityes.FavoriteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM favorite_list")
    fun getAllFavoriteItems(): Flow<List<FavoriteItem>>

    @Insert
    fun insertFavorite(favoriteItem: FavoriteItem)

    @Delete
    fun deleteFavorite(favoriteItem: FavoriteItem)
}
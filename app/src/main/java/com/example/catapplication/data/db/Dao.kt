package com.example.catapplication.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.catapplication.data.db.entity.FavoriteItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM favorite_list")
    fun getAllItems(): Flow<List<FavoriteItem>>

    @Insert
    fun addItem(item: FavoriteItem)

    @Delete
    fun deleteItem(item: FavoriteItem)
}
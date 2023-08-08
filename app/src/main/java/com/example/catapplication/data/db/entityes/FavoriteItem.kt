package com.example.catapplication.data.db.entityes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_list")
data class FavoriteItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "url_image")
    val image: String? = null,
    @ColumnInfo(name = "cat_id")
    val catId: String? = null
)
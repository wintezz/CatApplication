package com.example.catapplication.presentation.models

import android.os.Parcelable
import com.example.catapplication.data.db.entityes.FavoriteItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatUiModel(
    val id: String,
    val imageUrl: String,
    val favoriteId: String?
): Parcelable
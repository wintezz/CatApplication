package com.example.catapplication.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatModel(
    val id: String?,
    val imageUrl: String?,
    val favoriteId: String?
) : Parcelable
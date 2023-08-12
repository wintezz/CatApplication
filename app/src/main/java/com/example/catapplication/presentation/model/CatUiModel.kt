package com.example.catapplication.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatUiModel(
    val id: String?,
    val imageUrl: String?,
    val favoriteId: String?
) : Parcelable
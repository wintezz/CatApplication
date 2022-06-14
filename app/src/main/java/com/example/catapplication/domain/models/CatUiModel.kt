package com.example.catapplication.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CatUiModel(
    val id: String,
    val imageUrl: String,
    val favoriteId: String?
): Parcelable
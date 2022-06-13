package com.example.catapplication.domain

import com.example.catapplication.data.remote.Cat
import com.example.catapplication.presentation.models.CatUiModel

fun Cat.toUiModel(): CatUiModel? {
    return if (id != null && imageUrl != null) {
        try {
            CatUiModel(
                id = id,
                imageUrl = imageUrl,
                favoriteId = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    } else null
}
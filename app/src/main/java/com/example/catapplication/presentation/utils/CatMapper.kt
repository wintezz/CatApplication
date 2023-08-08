package com.example.catapplication.presentation.utils

import com.example.catapplication.data.remote.model.Cat
import com.example.catapplication.presentation.model.CatUiModel

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
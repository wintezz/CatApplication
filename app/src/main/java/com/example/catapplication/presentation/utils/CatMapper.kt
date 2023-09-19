package com.example.catapplication.presentation.utils

import com.example.catapplication.data.remote.model.Cat
import com.example.catapplication.presentation.model.CatModel

fun Cat.toUiModel(): CatModel {
    return CatModel(
        id = id,
        imageUrl = imageUrl,
        favoriteId = null
    )
}
package com.example.catapplication.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class Cat(
    @Json(name = "id")
    val id: String?,
    @Json(name = "url")
    val imageUrl: String?
)

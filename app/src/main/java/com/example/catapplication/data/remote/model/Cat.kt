package com.example.catapplication.data.remote.model

import com.google.gson.annotations.SerializedName

data class Cat(
    @SerializedName("id")
    val id: String?,
    @SerializedName("url")
    val imageUrl: String?
)
package com.example.catapplication.data.remote.retrofit

import com.example.catapplication.data.remote.model.Cat
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v1/images/search")
    suspend fun getListOfCats(
        @Query("limit") limit: Int = DEFAULT_PAGE_SIZE,
        @Query("page") page: Int = 0,
        @Query("mime_types") format: String = "jpg",
        @Query("order") order: String = "ACS"
    ): List<Cat>

    companion object {
        const val DEFAULT_PAGE_SIZE = 10
        const val API_KEY = "8cc12c9e-a406-4489-a189-0ef201b10714"
        const val CAT_EXTRA = "cat_extra"
        const val BASE_URL = "https://api.thecatapi.com"
    }
}
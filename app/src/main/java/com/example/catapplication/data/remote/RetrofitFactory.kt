package com.example.catapplication.data.remote

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitFactory {
    companion object {
        fun getRetroInstance(): Retrofit {
            val baseURL = "https://api.thecatapi.com"

            return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(baseURL)
                .build()
        }
    }
}

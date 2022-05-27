package com.example.catapplication.repository.retrofit

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

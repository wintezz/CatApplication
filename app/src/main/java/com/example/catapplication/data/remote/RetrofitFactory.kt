package com.example.catapplication.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitFactory {
    companion object {
        fun getRetroInstance(): Retrofit {
            val baseURL = "https://api.thecatapi.com"

            return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .client(OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    })
                    .build())
                .baseUrl(baseURL)
                .build()
        }
    }
}

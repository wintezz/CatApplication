package com.example.catapplication.data.remote.repository

import androidx.paging.PagingData
import com.example.catapplication.presentation.model.CatModel
import kotlinx.coroutines.flow.Flow


interface Repository {
    fun getCats(): Flow<PagingData<CatModel>>
}
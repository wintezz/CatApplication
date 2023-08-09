package com.example.catapplication.data.remote.repository

import androidx.paging.PagingData
import com.example.catapplication.presentation.model.CatUiModel
import kotlinx.coroutines.flow.Flow


interface Repository {
    fun getCats(): Flow<PagingData<CatUiModel>>
}
package com.example.catapplication.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.catapplication.data.remote.CatApiService
import com.example.catapplication.data.remote.RetrofitFactory
import com.example.catapplication.domain.CatPaging
import com.example.catapplication.domain.models.CatUiModel
import kotlinx.coroutines.flow.Flow

object CatRepository {

    private val apiService: CatApiService = RetrofitFactory.getRetroInstance()
        .create(CatApiService::class.java)

    fun getCats(): Flow<PagingData<CatUiModel>> {
        return Pager(config = PagingConfig(
            pageSize = CatApiService.DEFAULT_PAGE_SIZE,
            enablePlaceholders = true,
            maxSize = 100
        ),
            pagingSourceFactory = { CatPaging(apiService) }
        ).flow
    }
}
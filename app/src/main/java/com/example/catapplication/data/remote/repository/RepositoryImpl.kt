package com.example.catapplication.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.catapplication.data.remote.retrofit.ApiService
import com.example.catapplication.data.remote.retrofit.CatPaging
import com.example.catapplication.presentation.model.CatUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : Repository {

    override fun getCats(): Flow<PagingData<CatUiModel>> {
        return Pager(config = PagingConfig(
            pageSize = ApiService.DEFAULT_PAGE_SIZE,
            enablePlaceholders = true,
            maxSize = 100
        ),
            pagingSourceFactory = { CatPaging(apiService) }
        ).flow
    }
}
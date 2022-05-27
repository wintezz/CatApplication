package com.example.catapplication.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.catapplication.repository.retrofit.entity.CatApiService
import com.example.catapplication.repository.retrofit.RetrofitFactory
import com.example.catapplication.presentation.adapter.CatPaging
import com.example.catapplication.repository.Cat
import kotlinx.coroutines.flow.Flow

class CatViewModel : ViewModel() {

    private var apiService: CatApiService = RetrofitFactory.getRetroInstance()
        .create(CatApiService::class.java)

    val catList: Flow<PagingData<Cat>> = Pager(config = PagingConfig(
        pageSize = CatApiService.DEFAULT_PAGE_SIZE,
        enablePlaceholders = true,
        maxSize = 100
    ),
        pagingSourceFactory = { CatPaging(apiService) }

    ).flow.cachedIn(viewModelScope)

}

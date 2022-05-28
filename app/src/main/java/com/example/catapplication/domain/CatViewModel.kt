package com.example.catapplication.domain

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.catapplication.presentation.adapter.CatPaging
import com.example.catapplication.presentation.db.MainDataBase
import com.example.catapplication.presentation.db.entityes.FavoriteItem
import com.example.catapplication.repository.Cat
import com.example.catapplication.repository.retrofit.RetrofitFactory
import com.example.catapplication.repository.retrofit.entity.CatApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CatViewModel(database: MainDataBase) : ViewModel() {

    private val apiService: CatApiService = RetrofitFactory.getRetroInstance()
        .create(CatApiService::class.java)

    val catList: Flow<PagingData<Cat>> = Pager(config = PagingConfig(
        pageSize = CatApiService.DEFAULT_PAGE_SIZE,
        enablePlaceholders = true,
        maxSize = 100
    ),
        pagingSourceFactory = { CatPaging(apiService) }

    ).flow.cachedIn(viewModelScope)

    // DataBase
    val dao = database.getDao()

    val getFavoriteItem:LiveData<List<FavoriteItem>> = dao.getAllFavoriteItems().asLiveData()

    fun insertFavoriteItem(favoriteItem: FavoriteItem) = viewModelScope.launch {
        dao.insertFavorite(favoriteItem)
    }

    fun deleteFavoriteItem(favoriteItem: FavoriteItem) = viewModelScope.launch {
        dao.deleteFavorite(favoriteItem)
    }

    class MainViewModelFactory(private val database: MainDataBase): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(CatViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return CatViewModel(database) as T
            }
            throw IllegalArgumentException("Unknows ViewModelClass")
        }
    }
}




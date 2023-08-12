package com.example.catapplication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.catapplication.data.db.entity.FavoriteItem
import com.example.catapplication.data.db.repository.FavoriteRepository
import com.example.catapplication.data.remote.repository.Repository
import com.example.catapplication.presentation.model.CatUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val repository: Repository,
    private val repositoryFavorite: FavoriteRepository
) : ViewModel() {

    val catList: Flow<PagingData<CatUiModel>> by lazy {
        repository.getCats().cachedIn(viewModelScope)
    }

    val getFavoriteItem: LiveData<List<FavoriteItem>> = repositoryFavorite.getAllItems().asLiveData()

    fun onFavoriteItemClick(cat: CatUiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteItem.value?.let { items ->
                items.firstOrNull { it.catId == cat.id }?.let { favoriteItem ->
                    repositoryFavorite.deleteItem(favoriteItem)
                } ?: repositoryFavorite.addItem(cat)
            } ?: repositoryFavorite.addItem(cat)
        }
    }
}
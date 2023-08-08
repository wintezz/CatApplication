package com.example.catapplication.presentation.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.catapplication.data.db.MainDataBase
import com.example.catapplication.data.db.entityes.FavoriteItem
import com.example.catapplication.data.remote.repository.CatRepository
import com.example.catapplication.data.db.repository.FavoriteRepository
import com.example.catapplication.domain.models.CatUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CatViewModel(database: MainDataBase) : ViewModel() {

    private val repository = CatRepository
    private val repositoryFavorite = FavoriteRepository

    val catList: Flow<PagingData<CatUiModel>> by lazy {
        repository.getCats().cachedIn(viewModelScope)
    }

    private val _navigate = MutableLiveData<Navigate>()
    val navigate: LiveData<Navigate> = _navigate

    val getFavoriteItem: LiveData<List<FavoriteItem>> = repositoryFavorite.getAll().asLiveData()

    fun onItemClick(cat: CatUiModel) {
        _navigate.value = Navigate.ToDetail(cat)
    }

    init {
        getFavoriteItem.value
    }

    fun onFavoriteItemClick(cat: CatUiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteItem.value?.let { items ->
                items.firstOrNull { it.catId == cat.id }?.let { favoriteItem ->
                    repositoryFavorite.removeFavorite(favoriteItem)
                } ?: repositoryFavorite.addFavorite(cat)
            } ?: repositoryFavorite.addFavorite(cat)
        }
    }

    class MainViewModelFactory(private val database: MainDataBase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CatViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CatViewModel(database) as T
            }
            throw IllegalArgumentException("Unknowns ViewModelClass")
        }
    }

    sealed class Navigate {
        class ToDetail(val cat: CatUiModel) : Navigate()
        class Back : Navigate()
    }
}
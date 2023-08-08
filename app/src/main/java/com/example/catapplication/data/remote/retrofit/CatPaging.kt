package com.example.catapplication.data.remote.retrofit

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.catapplication.presentation.model.CatUiModel
import com.example.catapplication.presentation.utils.toUiModel
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CatPaging @Inject constructor(
    private val apiService: ApiService,
) : PagingSource<Int, CatUiModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatUiModel> = try {

        val pageNumber = params.key ?: 0
        val response = apiService.getListOfCats(page = pageNumber)

        val prevKey = if (pageNumber > 0) pageNumber - 1 else null
        val nextKey = if (response.isNotEmpty()) pageNumber + 1 else null

        LoadResult.Page(
            data = response.mapNotNull { it.toUiModel() },
            prevKey = prevKey,
            nextKey = nextKey
        )
    } catch (e: IOException) {
        LoadResult.Error(e)

    } catch (e: HttpException) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, CatUiModel>): Int? {
        return state.anchorPosition
    }
}
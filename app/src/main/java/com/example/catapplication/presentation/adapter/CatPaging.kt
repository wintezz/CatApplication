package com.example.catapplication.presentation.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.catapplication.repository.retrofit.entity.CatApiService
import com.example.catapplication.repository.Cat
import retrofit2.HttpException
import java.io.IOException

class CatPaging(
    private val myBackend: CatApiService,
) : PagingSource<Int, Cat>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> = try {

        val pageNumber = params.key ?: 0
        val response = myBackend.getListOfCats(page = pageNumber)

        val prevKey = if (pageNumber > 0) pageNumber - 1 else null
        val nextKey = if (response.isNotEmpty()) pageNumber + 1 else null

        LoadResult.Page(
            data = response,
            prevKey = prevKey,
            nextKey = nextKey
        )
    } catch (e: IOException) {
        LoadResult.Error(e)

    } catch (e: HttpException) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        return state.anchorPosition
    }
}

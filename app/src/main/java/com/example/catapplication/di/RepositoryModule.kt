package com.example.catapplication.di

import com.example.catapplication.CatApplication
import com.example.catapplication.data.db.Dao
import com.example.catapplication.data.db.repository.FavoriteRepository
import com.example.catapplication.data.db.repository.FavoriteRepositoryImpl
import com.example.catapplication.data.remote.repository.Repository
import com.example.catapplication.data.remote.repository.RepositoryImpl
import com.example.catapplication.data.remote.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): Repository {
        return RepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(dao: Dao): FavoriteRepository {
        return FavoriteRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideDao(): Dao = CatApplication.dataBase.getDao()
}
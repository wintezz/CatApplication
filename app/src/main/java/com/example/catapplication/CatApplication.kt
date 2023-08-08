package com.example.catapplication

import android.app.Application
import com.example.catapplication.data.db.MainDataBase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CatApplication : Application() {

    companion object {
        private var _dataBase: MainDataBase? = null
        val dataBase by lazy { _dataBase!! }
    }

    override fun onCreate() {
        super.onCreate()
        _dataBase = MainDataBase.getDataBase(this)
    }
}
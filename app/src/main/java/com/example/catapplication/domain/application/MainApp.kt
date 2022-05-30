package com.example.catapplication.domain.application

import android.app.Application
import com.example.catapplication.data.db.MainDataBase

class MainApp : Application() {

    val dataBase by lazy { MainDataBase.getDataBase(this) }
}
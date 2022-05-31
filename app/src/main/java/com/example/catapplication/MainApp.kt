package com.example.catapplication

import android.app.Application
import com.example.catapplication.data.db.MainDataBase

class MainApp : Application() {

    val dataBase by lazy { MainDataBase.getDataBase(this) }
}
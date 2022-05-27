package com.example.catapplication

import android.app.Application
import com.example.catapplication.presentation.db.MainDataBase

class MainApp: Application(){
    val database by lazy { MainDataBase.getDataBase(this)}
}
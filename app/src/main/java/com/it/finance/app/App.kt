package com.it.finance.app

import android.app.Application
import androidx.room.Room
import com.it.finance.db.AppDao
import com.it.finance.db.AppDataBase

class App : Application() {

    private val dbName = "settings"

    companion object {
        var database: AppDao? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            dbName
        ).build().getAppDao()
    }
}
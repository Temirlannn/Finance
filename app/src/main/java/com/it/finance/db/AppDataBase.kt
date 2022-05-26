package com.it.finance.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.it.finance.model.Profit

@Database(
    entities = [
        Profit::class
    ],
    version = 1
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun getAppDao(): AppDao

    abstract fun getProfileDao(): AppDao
}
package com.it.finance.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.it.finance.model.Payment
import com.it.finance.model.User

@Database(
    entities = [
        User::class,
        Payment::class
    ],
    version = 1
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun getAppDao(): AppDao
}
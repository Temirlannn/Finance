package com.it.finance.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.it.finance.model.User

@Dao
interface AppDao {

    @Query("SELECT * FROM USER WHERE username=:username AND password=:password")
    suspend fun login(username: String, password: String): User

    @Insert
    suspend fun addUser(user:User)
}
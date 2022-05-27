package com.it.finance.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.it.finance.model.Payment
import com.it.finance.model.User

@Dao
interface AppDao {

    @Query("SELECT * FROM user WHERE username=:username AND password=:password LIMIT 1")
    suspend fun login(username: String, password: String): User?

    @Query("SELECT * FROM payment WHERE payment_type=:paymentType and username_of_use=:username")
    suspend fun getHistories(paymentType: String,username: String): List<Payment>

    @Query("UPDATE user SET capital = capital+:amount where username=:username ")
    suspend fun submitPayment(amount: Int,username: String)


    @Query("SELECT * FROM user WHERE username=:username")
    suspend fun getUser(username: String): User


    @Query("SELECT * FROM payment WHERE category=='FAMILY' AND username_of_use=:username")
    suspend fun getFamilyPayments(username: String): List<Payment>

    @Query("SELECT * FROM payment WHERE category=='EDUCATION' AND username_of_use=:username")
    suspend fun getEducationPayments(username: String): List<Payment>

    @Query("SELECT * FROM payment WHERE category=='HOBBY' AND username_of_use=:username")
    suspend fun getHobbyPayments(username: String): List<Payment>

    @Query("SELECT * FROM payment WHERE category=='SELF_INTERESTS' AND username_of_use=:username")
    suspend fun getSelfInterestsPayments(username: String): List<Payment>

    @Insert(onConflict = REPLACE)
    suspend fun addUser(user: User): Long

    @Insert(onConflict = REPLACE)
    suspend fun addPayment(payment: Payment): Long
}
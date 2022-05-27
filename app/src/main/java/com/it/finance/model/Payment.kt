package com.it.finance.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Payment")
data class Payment(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "address")
    val address: String,

    @ColumnInfo(name = "username_of_use")
    val usernameOfUse:String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "amount")
    val amount: Int,

    @ColumnInfo(name = "payment_type")
    val paymentType: String
)

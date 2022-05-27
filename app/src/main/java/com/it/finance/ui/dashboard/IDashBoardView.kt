package com.it.finance.ui.dashboard

import com.it.finance.enums.CategoryType
import com.it.finance.model.User

interface IDashBoardView {
    fun updateCategories(type:CategoryType,result:Int)

    fun updateProfit(profit:Int)
    fun updateExpense(expense:Int)

    fun updateAccount(user: User)
}
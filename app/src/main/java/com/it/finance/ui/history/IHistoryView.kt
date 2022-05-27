package com.it.finance.ui.history

import com.it.finance.model.Payment

interface IHistoryView {
    fun setupRecycler(profitHistory:List<Payment>, expenseHistory:List<Payment>)
}
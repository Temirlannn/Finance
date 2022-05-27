package com.it.finance.ui.payment

import com.it.finance.model.Payment

interface IPaymentPresenter {
    fun kill()
    fun savePayment(payment:Payment)
}
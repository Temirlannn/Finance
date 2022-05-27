package com.it.finance.ui.payment

import com.it.finance.app.App.Companion.database
import com.it.finance.enums.PaymentType
import com.it.finance.model.Payment
import com.it.finance.util.Manager.username
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class PaymentPresenter(
    private val iPaymentView: IPaymentView,
    private val uiContext: CoroutineContext = Dispatchers.Main
) : IPaymentPresenter, CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = uiContext + job

    private fun detachView() {
        job.cancel()
    }

    override fun kill() {
        detachView()
    }

    override fun savePayment(payment: Payment) {
        CoroutineScope(coroutineContext).launch {
            database?.addPayment(payment)
            when (payment.paymentType) {
                PaymentType.EXPENSE.type -> database?.submitPayment(
                    payment.amount * -1,
                    username = username
                )
                PaymentType.PROFIT.type -> database?.submitPayment(payment.amount, username)
            }
            iPaymentView.closeFragment()
        }
    }

}
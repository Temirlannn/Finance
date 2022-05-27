package com.it.finance.ui.history

import com.it.finance.enums.PaymentType
import com.it.finance.app.App.Companion.database
import com.it.finance.util.Manager.username
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class HistoryPresenter(
    private val iHistoryView: IHistoryView,
    private val uiContext: CoroutineContext = Dispatchers.Main
) : IHistoryPresenter, CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = uiContext + job
    fun detachView() {
        job.cancel()
    }

    override fun kill() {
        detachView()
    }

    override fun getHistories() {
        CoroutineScope(coroutineContext).launch {
            val profits = database?.getHistories(PaymentType.PROFIT.type,username)
            val expense = database?.getHistories(PaymentType.EXPENSE.type, username)
            if (profits != null && expense != null) {
                iHistoryView.setupRecycler(profits, expense)
            }
        }
    }
}
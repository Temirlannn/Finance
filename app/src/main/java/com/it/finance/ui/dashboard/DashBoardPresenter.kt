package com.it.finance.ui.dashboard

import com.it.finance.enums.PaymentType
import com.it.finance.app.App.Companion.database
import com.it.finance.enums.CategoryType
import com.it.finance.model.Payment
import com.it.finance.util.Manager.username
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class DashBoardPresenter(
    private val IDashBoardView: IDashBoardView,
    private val uiContext: CoroutineContext = Dispatchers.Main
) : IDashBoardPresenter, CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = uiContext + job

    private fun detachView() {
        job.cancel()
    }

    override fun setUpUi() {
        CoroutineScope(coroutineContext).launch {
            val user = database?.getUser(username)
            val familyPayments = database?.getFamilyPayments(username)
            val educationPayments = database?.getEducationPayments(username)
            val selfInterestPayments = database?.getSelfInterestsPayments(username)
            val hobbyPayments = database?.getHobbyPayments(username)
            val profits = database?.getHistories(PaymentType.PROFIT.type, username)
            val expenses = database?.getHistories(PaymentType.EXPENSE.type, username)

            familyPayments?.let { calculateCategories(familyPayments, CategoryType.FAMILY) }

            hobbyPayments?.let { calculateCategories(hobbyPayments, CategoryType.HOBBY) }

            selfInterestPayments?.let {
                calculateCategories(
                    selfInterestPayments,
                    CategoryType.SELF_INTEREST
                )
            }

            educationPayments?.let {
                calculateCategories(
                    educationPayments,
                    CategoryType.EDUCATION
                )
            }

            profits?.let { calculateProfits(profits) }


            expenses?.let { calculateExpenses(expenses) }


            user?.let { IDashBoardView.updateAccount(user) }

        }
    }

    override fun kill() {
        detachView()
    }

    private fun calculateProfits(profits: List<Payment>) {
        var profit = 0
        profits.forEach {
            profit -= it.amount
        }
        IDashBoardView.updateProfit(profit)
    }

    private fun calculateExpenses(expenses: List<Payment>) {
        var expense = 0
        expenses.forEach {
            expense -= it.amount
        }
        IDashBoardView.updateExpense(expense)
    }

    private fun calculateCategories(payments: List<Payment>, category: CategoryType) {
        var positive = 0
        var negative = 0
        payments.forEach {
            if (PaymentType.findByType(it.paymentType) == PaymentType.EXPENSE.type) {
                negative -= it.amount
            } else positive -= it.amount
        }
        IDashBoardView.updateCategories(category, positive - negative)
    }
}
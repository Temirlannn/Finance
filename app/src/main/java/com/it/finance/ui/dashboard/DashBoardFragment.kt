package com.it.finance.ui.dashboard

import android.annotation.SuppressLint
import androidx.navigation.findNavController
import com.it.finance.R
import com.it.finance.databinding.FragmentDashBoardBinding
import com.it.finance.enums.CategoryType
import com.it.finance.model.User
import com.it.finance.ui.base.BaseFragment
import kotlin.math.abs

class DashBoardFragment : BaseFragment<FragmentDashBoardBinding>(), IDashBoardView {

    private val presenter: DashBoardPresenter by lazy {
        DashBoardPresenter(this)
    }

    override fun setupView() = with(binding) {
        presenter.setUpUi()
        history.setOnClickListener {
            it.findNavController().navigate(R.id.historyFragment)
        }
        payment.setOnClickListener {
            it.findNavController().navigate(R.id.paymentFragment)
        }
    }

    override fun updateCategories(type: CategoryType, result: Int) = with(binding) {
        val num = abs(result)
        when (type) {
            CategoryType.FAMILY -> {
                famVal.text = num.toString()
            }
            CategoryType.EDUCATION -> {
                eduVal.text = num.toString()
            }

            CategoryType.HOBBY -> {
                hooVal.text = num.toString()
            }
            CategoryType.SELF_INTEREST -> {
                selfVal.text = num.toString()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun updateProfit(profit: Int) = with(binding) {
        profitSom.text = "${abs(profit)} som"
    }

    @SuppressLint("SetTextI18n")
    override fun updateExpense(expense: Int) = with(binding) {
        expenseSom.text = "${abs(expense)} som"
    }

    @SuppressLint("SetTextI18n")
    override fun updateAccount(user: User) = with(binding) {
        nameValue.text = user.name
        usernameValue.text = user.username
        account.text = "${user.capital} som"
    }
}
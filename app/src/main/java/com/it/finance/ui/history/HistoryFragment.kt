package com.it.finance.ui.history

import com.google.android.material.tabs.TabLayoutMediator
import com.it.finance.databinding.FragmentHistoryBinding
import com.it.finance.model.Payment
import com.it.finance.ui.base.BaseFragment
import com.it.finance.ui.history.adapter.HistoryPagerAdapter

class HistoryFragment : BaseFragment<FragmentHistoryBinding>(), IHistoryView {


    private val presenter: HistoryPresenter by lazy {
        HistoryPresenter(this)
    }

    private val tabTitles: List<String> by lazy {
        listOf(
            "Profit",
            "Expense"
        )
    }

    override fun setupView() {
        presenter.getHistories()
    }

    override fun setupRecycler(profitHistory: List<Payment>, expenseHistory: List<Payment>) {

        val historyPagerAdapter = HistoryPagerAdapter(
            this@HistoryFragment, profitHistory, expenseHistory
        )
        binding.viewpager.apply {
            adapter = historyPagerAdapter
        }
        val mediator = TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = tabTitles[position]
        }
        if (!mediator.isAttached) mediator.attach()
    }
}
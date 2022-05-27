package com.it.finance.ui.history

import androidx.recyclerview.widget.LinearLayoutManager
import com.it.finance.databinding.FragmentHistorypageBinding
import com.it.finance.model.Payment
import com.it.finance.ui.base.BaseFragment
import com.it.finance.ui.history.adapter.HistoryAdapter

class HistoryPageFragment(private val histories: List<Payment>) :
    BaseFragment<FragmentHistorypageBinding>() {

    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter()
    }

    override fun setupView() {
        historyAdapter.histories = histories
        binding.recycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext())
            adapter = historyAdapter
        }
    }

}
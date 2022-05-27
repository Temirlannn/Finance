package com.it.finance.ui.history.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.it.finance.model.Payment
import com.it.finance.ui.history.HistoryPageFragment

class HistoryPagerAdapter(
    fragment: Fragment,
    private val usaRules: List<Payment>,
    private val canadaRules: List<Payment>
) : FragmentStateAdapter(fragment) {
    companion object {
        const val NUMBER_OF_PAGES = 2
    }

    override fun getItemCount(): Int = NUMBER_OF_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HistoryPageFragment(usaRules)
            else -> HistoryPageFragment(canadaRules)
        }
    }
}
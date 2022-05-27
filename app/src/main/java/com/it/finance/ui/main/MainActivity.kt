package com.it.finance.ui.main

import androidx.navigation.fragment.NavHostFragment
import com.it.finance.R
import com.it.finance.databinding.ActivityMainBinding
import com.it.finance.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun setupView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
    }

    override fun bindViewModel() {
    }
}

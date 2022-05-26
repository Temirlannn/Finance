package com.it.finance.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.it.finance.R
import com.it.finance.databinding.ActivityLoginBinding
import com.it.finance.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
}

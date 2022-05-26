package com.it.finance.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.it.finance.databinding.ActivityLoginBinding
import com.it.finance.ui.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(), ILoginView {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private lateinit var binding: ActivityLoginBinding
    private val presenter by lazy { LoginPresenter(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() = with(binding) {
        btnSave.setOnClickListener {
            uiScope.launch {
                val (isSuccess, errorMessage) = presenter.login(
                    username.text.toString(),
                    password.text.toString()
                )

                when {
                    isSuccess -> {
                        runOnUiThread {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        }
                    }

                    errorMessage != null -> {
                        showErrorText(errorMessage)
                    }
                }
            }
        }
    }

    override fun showErrorText(text: Int) {
        Toast.makeText(this, getString(text), Toast.LENGTH_SHORT).show()
    }
}
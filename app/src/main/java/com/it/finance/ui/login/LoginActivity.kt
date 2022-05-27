package com.it.finance.ui.login

import android.content.Intent
import android.widget.Toast
import com.it.finance.databinding.ActivityLoginBinding
import com.it.finance.ui.base.BaseActivity
import com.it.finance.ui.main.MainActivity
import com.it.finance.ui.registration.RegistrationActivity
import com.it.finance.util.Manager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate),
    ILoginView {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private val presenter by lazy { LoginPresenter(this) }
    override fun setupView() = with(binding) {
        btnSave.setOnClickListener {
            uiScope.launch {
                val (isSuccess, errorMessage) = presenter.login(
                    username.text.toString().trim(),
                    password.text.toString().trim()
                )

                when {
                    isSuccess -> {
                        runOnUiThread {
                            Manager.username = username.text.toString().trim()
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        }
                    }

                    errorMessage != null -> {
                        showErrorText(errorMessage)
                    }
                }
            }
        }

        otvet.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
        }
    }

    override fun showErrorText(text: Int) {
        Toast.makeText(this, getString(text), Toast.LENGTH_SHORT).show()
    }

    override fun bindViewModel() {
    }
}
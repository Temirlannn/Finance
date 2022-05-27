package com.it.finance.ui.registration

import android.content.Intent
import com.it.finance.databinding.ActivityRegistrationBinding
import com.it.finance.ui.base.BaseActivity
import com.it.finance.ui.main.MainActivity
import com.it.finance.util.Manager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegistrationActivity :
    BaseActivity<ActivityRegistrationBinding>(ActivityRegistrationBinding::inflate), IRegisterView {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private val presenter by lazy { RegisterPresenter(this) }


    override fun setupView() = with(binding) {
        btnRegister.setOnClickListener {
            uiScope.launch {
                presenter.register(
                    username = username.text.toString().trim(),
                    password = password.text.toString().trim(),
                    name = name.text.toString()
                )
                runOnUiThread {
                    Manager.username = username.text.toString().trim()
                    startActivity(Intent(this@RegistrationActivity, MainActivity::class.java))
                }
            }
        }
    }

    override fun showErrorText(text: Int) {

    }

    override fun bindViewModel() {
    }
}
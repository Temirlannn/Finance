package com.it.finance.ui.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.it.finance.databinding.ActivityLoginBinding
import com.it.finance.databinding.ActivityMainBinding
import com.it.finance.databinding.ActivityRegistrationBinding
import com.it.finance.ui.login.LoginPresenter
import com.it.finance.ui.main.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegistrationActivity : AppCompatActivity(), IRegisterView {

    private lateinit var binding: ActivityRegistrationBinding

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private val presenter by lazy { RegisterPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() = with(binding) {
        btnRegister.setOnClickListener {
            uiScope.launch {
                presenter.register(
                    username = username.text.toString(),
                    password = password.text.toString(),
                    name = name.text.toString()
                )
                runOnUiThread {
                    startActivity(Intent(this@RegistrationActivity, MainActivity::class.java))
                }
            }
        }
    }

    override fun showErrorText(text: String) {

    }
}
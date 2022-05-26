package com.it.finance.ui.login

import com.it.finance.R
import com.it.finance.app.App.Companion.database


class LoginPresenter(private val iMainView: ILoginView) : ILoginPresenter {
    override suspend fun login(username: String, password: String): Pair<Boolean, Int?> {
        database?.login(username, password)?.let {
            return@login Pair(true, null)
        }
        return Pair(false, R.string.mynda_jok_suka)
    }
}
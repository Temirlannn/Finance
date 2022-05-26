package com.it.finance.ui.registration

import com.it.finance.app.App.Companion.database
import com.it.finance.model.User


class RegisterPresenter(private val iMainView: IRegisterView) : IRegisterPresenter {
    override suspend fun register(
        username: String,
        password: String,
        name: String
    ) {
        val user =
            User(name = name, username = username, password = password, id = 0, capital = 5000)
        database?.addUser(user)
    }
}
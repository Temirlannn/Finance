package com.it.finance.ui.login


interface ILoginPresenter {
    suspend fun login(username: String, password: String): Pair<Boolean, Int?>
}
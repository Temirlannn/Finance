package com.it.finance.ui.registration


interface IRegisterPresenter {
    suspend fun register(username: String, password: String,name:String)
}
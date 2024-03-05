package com.example.lopuxi.ui.screens.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

interface AuthRegViewModelInt {

    fun setLogin(login: String)

    fun setPassword(password: String)

    fun submitPassword(password: String)

    fun authorize()

    fun register()

}

sealed interface AuthorizationState {
    data class Success(val accessToken: String): AuthorizationState

    object Error: AuthorizationState

    object Loading: AuthorizationState

    object Default: AuthorizationState
}

class AuthRegViewModel(): ViewModel(), AuthRegViewModelInt {

    var authorizationState: AuthorizationState by mutableStateOf(AuthorizationState.Default)
        private set

    private var login: String = ""

    private var password: String = ""

    private var arePasswordsEqual: Boolean = false

    override fun setLogin(login: String) {
        this.login = login
    }

    override fun setPassword(password: String) {
        this.password = password
    }

    override fun submitPassword(password: String) {
        arePasswordsEqual = this.password == password
    }
    override fun authorize() {
        return
    }

    override fun register() {
        return
    }

}
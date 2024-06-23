package com.project.smartgreen.screens.login


import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginViewModel(private val context: Context) : ViewModel() {

    var loginData by mutableStateOf(LoginData())

    private val _loginState = MutableStateFlow<LoginButtonState>(LoginButtonState.Ready)
    val loginState: StateFlow<LoginButtonState> = _loginState

    fun checkLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loginState.value = LoginButtonState.Loading
                delay(2000) // Simulating login data fetch
                if (checkCredentials()) {
                    _loginState.value = LoginButtonState.Success
                } else {
                    _loginState.value = LoginButtonState.Error(1)
                    loginData = LoginData()
                    withContext(Dispatchers.Main) {
                        // Handle error state if needed
                    }
                    _loginState.value = LoginButtonState.Ready
                }
            } catch (e: Exception) {
                _loginState.value = LoginButtonState.Error(2)
            }
        }
    }

    fun checkCredentials(): Boolean {
        return loginData.isNotEmpty() && loginData.login == "admin"
    }
}

sealed class LoginButtonState {
    object Ready : LoginButtonState()
    object Success : LoginButtonState()
    data class Error(val errorCode: Int) : LoginButtonState()
    object Loading : LoginButtonState()
}
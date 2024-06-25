package com.project.smartgreen.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.smartgreen.data.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> get() = _loginState

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> get() = _registerState

    fun login(username: String, password: String, role: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.value = LoginState.Loading
            try {
                Log.i("MainViewModel", "Attempting login with username: $username, password: $password, role: $role")
                val response = repository.login(username, password, role)
                Log.i("MainViewModel", response.toString())
                if (response.isSuccessful) {
                    val token = response.body()?.token ?: ""
                    Log.i("MainViewModel", "Login successful. Token: $token")
                    if (role == "user") {
                        _loginState.value = LoginState.Success(token, "home")
                    } else if (role == "admin") {
                        _loginState.value = LoginState.Success(token, "homeadmin")
                    }
                } else {
                    val errorMessage = response.message()
                    Log.i("MainViewModel", "Login failed. Error: $errorMessage")
                    _loginState.value = LoginState.Error(response.message())
                }
            } catch (e: Exception) {
                Log.i("MainViewModel", "Login exception: ${e.message}")
                _loginState.value = LoginState.Error("Network error")
            }
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            try {
                Log.i("MainViewModel", "Attempting registration with username: $username, password: $password")
                val response = repository.register(username, password)
                Log.i("MainViewModel", "Response: $response")
                if (response.isSuccessful) {
                    Log.i("MainViewModel", "Registration successful.")
                    _registerState.value = RegisterState.Success
                } else {
                    val errorMessage = response.message()
                    Log.i("MainViewModel", "Registration failed. Error: $errorMessage")
                    _registerState.value = RegisterState.Error(errorMessage)
                }
            } catch (e: Exception) {
                Log.i("MainViewModel", "Registration exception: ${e.message}")
                _registerState.value = RegisterState.Error("Network error")
            }
        }
    }
}

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val token: String, val screen: String) : LoginState()
    data class Error(val message: String) : LoginState()
}

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}

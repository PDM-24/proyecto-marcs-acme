package com.project.smartgreen.ui.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.smartgreen.data.model.Comment
import com.project.smartgreen.data.model.Crop
import com.project.smartgreen.data.repository.AuthRepository
import com.project.smartgreen.utils.PreferenceHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel(private val repository: AuthRepository,private val context: Context) : ViewModel() {
    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> get() = _loginState

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> get() = _registerState

    private val _cropsState = MutableStateFlow<List<Crop>>(emptyList())

    private val _commentsState = MutableStateFlow<CommentState>(CommentState.Idle)
    val cropsState: StateFlow<List<Crop>> get() = _cropsState

    val commentsState: StateFlow<CommentState> get() = _commentsState

    private val _addCropState = MutableStateFlow<AddCropState>(AddCropState.Idle)
    val addCropState: StateFlow<AddCropState> get() = _addCropState

    private var token: String? = null

    init {
        token = PreferenceHelper.getToken(context)
        Log.i("MainViewModel", "Token initialized: $token")
    }



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

                    PreferenceHelper.saveToken(context, token)
                    this@MainViewModel.token = token
                    Log.i("MainViewModel", "Token saved in preferences.")

                    _cropsState.value = emptyList()

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

    fun getCrops() {
        viewModelScope.launch {
            token?.let {
                Log.i("MainViewModel", "Getting crops with token: $it")
                try {
                    val response = repository.getCrops(it)
                    if (response.isSuccessful) {
                        val crops = response.body() ?: emptyList()
                        Log.i("MainViewModel", "Crops obtained: $crops")
                        _cropsState.value = crops
                    } else {
                        Log.i("MainViewModel", "Error getting crops: ${response.message()}")
                    }
                } catch (e: Exception) {
                    Log.i("MainViewModel", "Exception getting crops: ${e.message}")
                }
            } ?: run {
                Log.i("MainViewModel", "Token is null, cannot get crops")
            }
        }
    }

    fun addCrop(crop: Crop) {
        viewModelScope.launch {
            token?.let {
                _addCropState.value = AddCropState.Loading
                try {
                    val response = repository.addCrop(it, crop)
                    if (response.isSuccessful) {
                        _addCropState.value = AddCropState.Success
                        getCrops()
                        _addCropState.value = AddCropState.Idle// Actualizar la lista de cultivos
                    } else {
                        Log.i("MainViewModel", "Error adding crop: ${response.message()}")
                        Log.i("MainViewModel", "Response code: ${response.code()}")
                        Log.i("MainViewModel", "Response body: ${response.errorBody()?.string()}")
                        _addCropState.value = AddCropState.Error(response.message())
                    }
                } catch (e: Exception) {
                    Log.i("MainViewModel", "Exception adding crop: ${e.message}")
                    _addCropState.value = AddCropState.Error("Network error")
                }
            } ?: run {
                Log.i("MainViewModel", "Token is null, cannot add crop")
                _addCropState.value = AddCropState.Error("Token is null")
            }
        }
    }

    fun addComment(comment: Comment) {
        viewModelScope.launch {
            token?.let {
                _commentsState.value = CommentState.Loading
                try {
                    val response = repository.addComment(it, comment)
                    if (response.isSuccessful) {
                        _commentsState.value = CommentState.Success
                        getComments()
                        _commentsState.value = CommentState.Idle // Volver a estado Idle después de actualizar los comentarios
                        Log.i("MainViewModel", "Comment added successfully")
                    } else {
                        Log.i("MainViewModel", "Error adding comment: ${response.message()}")
                        _commentsState.value = CommentState.Error(response.message())
                    }
                } catch (e: Exception) {
                    Log.i("MainViewModel", "Exception adding comment: ${e.message}")
                    _commentsState.value = CommentState.Error("Network error")
                }
            } ?: run {
                Log.i("MainViewModel", "Token is null, cannot add comment")
                _commentsState.value = CommentState.Error("Token is null")
            }
        }
    }

    fun getComments() {
        viewModelScope.launch {
            token?.let {
                Log.i("MainViewModel", "Getting comments with token: $it")
                try {
                    val response = repository.getComments(it)
                    if (response.isSuccessful) {
                        val comments = response.body() ?: emptyList()
                        Log.i("MainViewModel", "Comments obtained: $comments")
                        _commentsState.value = CommentState.Loaded(comments)
                    } else {
                        Log.i("MainViewModel", "Error getting comments: ${response.message()}")
                        _commentsState.value = CommentState.Error(response.message())
                    }
                } catch (e: Exception) {
                    Log.i("MainViewModel", "Exception getting comments: ${e.message}")
                    _commentsState.value = CommentState.Error("Network error")
                }
            } ?: run {
                Log.i("MainViewModel", "Token is null, cannot get comments")
                _commentsState.value = CommentState.Error("Token is null")
            }
        }
    }

    fun clearCommentState() {
        _commentsState.value = CommentState.Idle
    }

    fun logout() {
        token = null
        _loginState.value = LoginState.Idle
        _cropsState.value = emptyList() // Limpiar los cultivos al cerrar sesión
        PreferenceHelper.clearToken(context) // Limpiar el token de las preferencias
        Log.i("MainViewModel", "Token cleared and user logged out")
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

sealed class AddCropState {
    object Idle : AddCropState()
    object Loading : AddCropState()
    object Success : AddCropState()
    data class Error(val message: String) : AddCropState()
}

sealed class CommentState {
    object Idle : CommentState()
    object Loading : CommentState()
    object Success : CommentState()
    data class Error(val message: String) : CommentState()
    data class Loaded(val comments: List<Comment>) : CommentState()
}
package com.project.smartgreen.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.smartgreen.data.repository.AuthRepository
import com.project.smartgreen.navigation.NavGraph
import com.project.smartgreen.ui.ViewModel.MainViewModel
import com.project.smartgreen.utils.RetrofitInstance


@Composable

fun MainScreen(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()
    val authService = RetrofitInstance.authService
    val authRepository = AuthRepository(authService)
    val mainViewModel = MainViewModel(authRepository)

    NavGraph(viewModel = mainViewModel, navController = navController)
}

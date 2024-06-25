package com.project.smartgreen.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.smartgreen.data.repository.AuthRepository
import com.project.smartgreen.navigation.NavGraph

import com.project.smartgreen.ui.viewmodel.MainViewModel
import com.project.smartgreen.ui.viewmodel.MainViewModelFactory
import com.project.smartgreen.utils.RetrofitInstance

@Composable
fun MainScreen(modifier: Modifier = Modifier, permission: Boolean = false, authRepository: AuthRepository) {
    val navController: NavHostController = rememberNavController()

    val context = LocalContext.current
    val mainViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(context, authRepository))


    NavGraph(viewModel = mainViewModel, permision = permission, navController = navController)
}

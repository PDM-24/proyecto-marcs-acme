package com.project.smartgreen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            //InicioSesionScreen(navController)
        }
        composable("bitacora") {
            //BitacoraScreen(navController)
        }
        composable("seleccion") {
            //SeleccionScreen(navController)
        }
        composable("registro") {
            //RegistroScreen(navController)
        }
        composable("registroc") {
            //RegistroCScreen(navController)
        }
        composable("graficasc") {
            //GraficasCScreen(navController)
        }
        composable("agregados") {
            //AgregadosScreen(navController)
        }
        composable("lista") {
            //ListaScreen(navController)
        }
        composable("home") {
            //HomeScreen(navController)
        }
        composable("seleccionarc") {
            //SeleccionarC(navController)
        }
        composable("loginuser") {
            val context = LocalContext.current
            //val viewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(context))
            //LoginFormComponent(viewModel = viewModel, navController=navController)
        }
    }
}

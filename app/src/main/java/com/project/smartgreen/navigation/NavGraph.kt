package com.project.smartgreen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.smartgreen.screens.SeleccionScreen.SeleccionScreen
import com.project.smartgreen.screens.bitacora.BitacoraScreen
import com.project.smartgreen.screens.features.AgregadosScreen
import com.project.smartgreen.screens.features.ListaScreen
import com.project.smartgreen.screens.graficas.GraficasCScreen
import com.project.smartgreen.screens.home.HomeScreen
import com.project.smartgreen.screens.login.InicioSesionScreen
import com.project.smartgreen.screens.login.LoginFormComponent
import com.project.smartgreen.screens.login.LoginViewModel
import com.project.smartgreen.screens.login.LoginViewModelFactory
import com.project.smartgreen.screens.registro.RegistroCScreen
import com.project.smartgreen.screens.registro.RegistroScreen
import com.project.smartgreen.screens.registro.SeleccionarC

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            InicioSesionScreen(navController)
        }
        composable("bitacora") {
            BitacoraScreen(navController)
        }
        composable("seleccion") {
            SeleccionScreen(navController)
        }
        composable("registro") {
            RegistroScreen(navController)
        }
        composable("registroc") {
            RegistroCScreen(navController)
        }
        composable("graficasc") {
            GraficasCScreen(navController)
        }
        composable("agregados") {
            AgregadosScreen(navController)
        }
        composable("lista") {
            ListaScreen(navController)
        }
        composable("home") {
            HomeScreen(navController)
        }
        composable("seleccionarc") {
            SeleccionarC(navController)
        }
        composable("loginuser") {
            val context = LocalContext.current
            val viewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(context))
            LoginFormComponent(viewModel = viewModel, navController=navController)
        }
    }
}

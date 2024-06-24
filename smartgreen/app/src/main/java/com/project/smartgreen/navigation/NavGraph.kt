package com.project.smartgreen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.smartgreen.screens.bitacora.BitacoraScreen
import com.project.smartgreen.screens.graficas.GraficasAdminScreen
import com.project.smartgreen.screens.graficas.GraficasCScreen
import com.project.smartgreen.screens.graficas.InformesGraficaScreen
import com.project.smartgreen.screens.home.HomeAdminScreen
import com.project.smartgreen.screens.home.HomeScreen
import com.project.smartgreen.screens.login.AdminLogin
import com.project.smartgreen.screens.login.SelectionLogin
import com.project.smartgreen.screens.login.UserLogin
import com.project.smartgreen.screens.login.UserRegister
import com.project.smartgreen.screens.mensajes.ComentariosScreen
import com.project.smartgreen.screens.mensajes.Mensaje1Screen
import com.project.smartgreen.screens.registro.SeleccionarC
import com.project.smartgreen.screens.registro.SeleccionarCAdmin
import com.project.smartgreen.ui.components.AgregadosScreen

import com.project.smartgreen.ui.components.InformesScreen
import com.project.smartgreen.ui.components.ListaScreen
import com.project.smartgreen.ui.components.RegistroCScreen
import com.project.smartgreen.ui.components.RegistroCultivoScreen
import com.project.smartgreen.ui.components.RegistroScreen
import com.project.smartgreen.ui.components.SeleccionScreen
import com.project.smartgreen.ui.components.UserAScreen
import com.project.smartgreen.ui.viewmodel.ComentariosViewModel
import com.project.smartgreen.ui.viewmodel.MainViewModel

@Composable
fun NavGraph(viewModel: MainViewModel, navController: NavHostController) {
    val comentariosViewModel: ComentariosViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            SelectionLogin(navController, viewModel)
        }
        composable("Userloginn") {
            UserLogin(navController, viewModel)
        }
        composable("UserRegisterr") {
            UserRegister(navController, viewModel)
        }
        composable("AdminLogin") {
            AdminLogin(navController, viewModel)
        }
        composable("bitacora") {
            BitacoraScreen(navController)
        }
        composable("seleccion") {
            SeleccionScreen(navController)
        }
        composable("registro") {
            RegistroScreen(navController, comentariosViewModel)
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

        composable("homeadmin") {
            HomeAdminScreen(navController)
        }

        composable("seleccionarc") {
            SeleccionarC(navController)
        }
        composable("mensaje") {
            Mensaje1Screen(navController)
        }
        composable("registrocultivo") {
            RegistroCultivoScreen(navController)
        }
        composable("comentarios") {
            ComentariosScreen(navController, comentariosViewModel)
        }

        composable("useradmin") {
            UserAScreen(navController, comentariosViewModel)
        }

        composable("seleccionarcadmin") {
            SeleccionarCAdmin(navController)
        }

        composable("graficasadmin") {
            GraficasAdminScreen(navController)
        }

        composable("informes") {
            InformesScreen(navController, comentariosViewModel)
        }

        composable("informesgrafica") {
            InformesGraficaScreen(navController)
        }
    }
}
package com.project.smartgreen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.smartgreen.screens.RegistroCultivo
import com.project.smartgreen.screens.Users.UserScreen
import com.project.smartgreen.screens.admin.Homeadmin
import com.project.smartgreen.screens.bitacora.BitacoraScreen
import com.project.smartgreen.screens.features.ShowCommentsScreen
import com.project.smartgreen.screens.features.ShowCropsScreen

import com.project.smartgreen.screens.graficas.GraficasAdminScreen
import com.project.smartgreen.screens.graficas.GraficasCScreen
import com.project.smartgreen.screens.graficas.InformesGraficaScreen
import com.project.smartgreen.screens.home.HomeAdminScreen
import com.project.smartgreen.screens.home.HomeScreen
import com.project.smartgreen.screens.login.UserLogin
import com.project.smartgreen.screens.login.UserRegister

import com.project.smartgreen.screens.mensajes.Mensaje1Screen
import com.project.smartgreen.screens.registro.SeleccionarC
import com.project.smartgreen.screens.registro.SeleccionarCAdmin
import com.project.smartgreen.ui.viewmodel.MainViewModelFactory
import com.project.smartgreen.ui.components.AgregadosScreen
import com.project.smartgreen.ui.components.InformesScreen
import com.project.smartgreen.ui.components.ListaScreen
import com.project.smartgreen.ui.components.RegistroCScreen
import com.project.smartgreen.ui.components.RegistroScreen
import com.project.smartgreen.ui.components.SeleccionScreen
import com.project.smartgreen.ui.components.UserAScreen
import com.project.smartgreen.ui.viewmodel.ComentariosViewModel
import com.project.smartgreen.ui.viewmodel.MainViewModel

@Composable
fun NavGraph(viewModel: MainViewModel, navController: NavHostController, permision: Boolean = false) {
    val comentariosViewModel: ComentariosViewModel = viewModel()

    NavHost(navController = navController, startDestination = "Userloginn") {

        composable("Userloginn") {
            UserLogin(navController, viewModel)
        }
        composable("UserRegisterr") {
            UserRegister(navController, viewModel)
        }
        composable("bitacora") {
            BitacoraScreen(navController)
        }
        composable("seleccion") {
            SeleccionScreen(navController)
        }
        composable("registro") {
            RegistroScreen(navController, viewModel)
        }
        composable("registroc") {
            RegistroCScreen(navController, permision, modifier = Modifier, comentariosViewModel)
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
            HomeScreen(navController, viewModel)
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
            RegistroCultivo(navController, viewModel)
        }
        composable("comentarios") {
            ShowCommentsScreen(navController, viewModel )
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

        composable("userscreen") {
            UserScreen(navController, comentariosViewModel)
        }
        composable("showcrops") {
            ShowCropsScreen(navController, viewModel)
        }



}

}
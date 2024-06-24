package com.project.smartgreen.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.smartgreen.navigation.NavGraph



@Composable
fun MainScreen(modifier: Modifier = Modifier, permission: Boolean = false){
    val navController: NavHostController = rememberNavController()
    NavGraph( permision = permission, navController = navController)
}
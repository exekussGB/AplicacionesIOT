package com.example.aplicacionesiot.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import com.example.aplicacionesiot.screens.HomeScreen
import com.example.aplicacionesiot.screens.LoginScreen
import com.example.aplicacionesiot.screens.RegisterScreen

@Composable
fun AppNavGraph() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = Route.Login.path) {
        composable(Route.Login.path) { LoginScreen(nav) }
        composable(Route.Register.path) { RegisterScreen(nav) }
        composable(Route.Home.path) { HomeScreen() }
    }
}

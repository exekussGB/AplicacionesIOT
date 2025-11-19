package com.example.aplicacionesiot.nav


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aplicacionesiot.R
import com.example.aplicacionesiot.screens.HomeScreen
import com.example.aplicacionesiot.screens.LoginScreen
import com.example.aplicacionesiot.screens.RegisterScreen

@Composable
fun AppNavGraph() {
    val nav = rememberNavController()
    NavHost(navController = nav, startDestination = "splash") {
        composable("splash") {
            SplashScreen {
                nav.navigate(Route.Login.path) {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }
        composable(Route.Login.path) { LoginScreen(nav) }
        composable(Route.Register.path) { RegisterScreen(nav) }
        composable(Route.Home.path) { HomeScreen() }
    }
}
@Composable
fun SplashScreen(onFinish: () -> Unit) {
    // Composable minimal (logo centrado y fondo de marca)
    LaunchedEffect(Unit) {
        // Seguridad extra: si por alguna razón ya no mantiene el Splash nativo,
        // forzamos un fallback de 200-400ms para transicionar suave:
        kotlinx.coroutines.delay(250L)
        onFinish()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_background), // o Image con painterResource
                    contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .align(Alignment.Center)
        )
    }
}

package com.example.aplicacionesiot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.aplicacionesiot.nav.AppNavGraph
import com.example.aplicacionesiot.ui.theme.AplicacionesIOTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // sin keepSplash ni delay manual
        super.onCreate(savedInstanceState)
        setContent {
            AplicacionesIOTTheme {
                AppNavGraph()
            }
        }
    }
}

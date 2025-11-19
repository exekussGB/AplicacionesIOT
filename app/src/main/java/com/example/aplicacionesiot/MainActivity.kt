package com.example.aplicacionesiot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.aplicacionesiot.nav.AppNavGraph
import com.example.aplicacionesiot.ui.theme.AplicacionesIOTTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var keepSplash = true // condición para mantener el splash visible

    override fun onCreate(savedInstanceState: Bundle?) {
        // 1) Instalar Splash antes de super.onCreate
        val splash = installSplashScreen()
        splash.setKeepOnScreenCondition { keepSplash }
        
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2) Simular/realizar inicialización breve (1.2 s)
        lifecycleScope.launch {
            delay(1200L)
            keepSplash = false
        }

        // 3) Contenido Compose
        setContent {
            AplicacionesIOTTheme {
                AppNavGraph() 
            }
        }
    }
}

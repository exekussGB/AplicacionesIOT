package com.example.aplicacionesiot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.aplicacionesiot.nav.AppNavGraph
import com.example.aplicacionesiot.ui.theme.AplicacionesIOTTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        class MainActivity : ComponentActivity() {
            private var keepSplash = true // condición para mantener el splash visible
            override fun onCreate(savedInstanceState: Bundle?) {
                // 1) Instalar Splash antes de super.onCreate
                val splash = installSplashScreen()
                splash.setKeepOnScreenCondition { keepSplash }
                super.onCreate(savedInstanceState)
                // 2) Simular/realizar inicialización breve (1–2 s)
                lifecycleScope.launchWhenCreated {
                    // Aquí podrías leer token, preferencias, etc.
                    kotlinx.coroutines.delay(1200L)
                    keepSplash = false
                }
                // 3) Contenido Compose
                setContent {
                    AplicacionesIOTTheme {
                        AppNavGraph() // Tu NavHost con rutas Splash->Home (abajo un ejemplo)
                    }
                }
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AplicacionesIOTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AplicacionesIOTTheme {
        Greeting("Android")
    }
}
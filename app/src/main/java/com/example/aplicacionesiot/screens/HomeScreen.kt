package com.example.aplicacionesiot.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplicacionesiot.data.remote.dto.UserDto
import com.example.aplicacionesiot.screens.login.AuthState
import com.example.aplicacionesiot.screens.login.AuthViewModel
import com.example.aplicacionesiot.ui.theme.AplicacionesIOTTheme

@Composable
fun HomeContent(authState: AuthState, onLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mostrar datos del usuario si están disponibles
        if (authState is AuthState.Authenticated) {
            val user = authState.user
            Text("Bienvenido ${user.name}", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(20.dp))
        }

        Button(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar sesión")
        }
    }
}

@Composable
fun HomeScreen(
    vm: AuthViewModel = viewModel(),
    onLogoutDone: () -> Unit
) {
    val authState by vm.authState.collectAsState()

    // Si después de logout el estado cambia a Unauthenticated, navegar
    LaunchedEffect(authState) {
        if (authState is AuthState.Unauthenticated) {
            onLogoutDone()
        }
    }

    HomeContent(
        authState = authState,
        onLogout = { vm.logout() }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    AplicacionesIOTTheme {
        HomeContent(
            authState = AuthState.Authenticated(
                user = UserDto(
                    id = 1,
                    name = "Javier Ahumada",
                    email = "javier@example.com"
                )
            ),
            onLogout = {}
        )
    }
}

package com.example.aplicacionesiot.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.aplicacionesiot.R
import com.example.aplicacionesiot.data.local.StoreUser
import com.example.aplicacionesiot.data.remote.api.AuthApi
import com.example.aplicacionesiot.data.repository.AuthRepository
import com.example.aplicacionesiot.nav.Route
import com.example.aplicacionesiot.ui.theme.AplicacionesIOTTheme
import com.example.aplicacionesiot.viewmodel.AuthViewModel
import com.example.aplicacionesiot.viewmodel.AuthViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun LoginContent(
    user: String,
    pass: String,
    onUserChange: (String) -> Unit,
    onPassChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Logo",
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Bienvenido", fontSize = 23.sp)
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(user, onUserChange, label = { Text("Usuario") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(pass, onPassChange, label = { Text("Contraseña") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))
        Button(onClick = onLoginClick, modifier = Modifier.fillMaxWidth()) {
            Text("Ingresar")
        }
        TextButton(onClick = onRegisterClick, modifier = Modifier.align(Alignment.End)) {
            Text("¿No tienes cuenta? Regístrate")
        }
    }
}

@Composable
fun LoginScreen(nav: NavController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    // Instancia "manual" de ViewModel (solo para demostración; idealmente usar Hilt/Koin)
    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/") // Asegúrate de usar tu IP o localhost correcto
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api = retrofit.create(AuthApi::class.java)
    val repo = AuthRepository(api)
    val storeUser = StoreUser(context) // Crear instancia de StoreUser
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(repo, storeUser))

    LoginContent(
        user = email,
        pass = pass,
        onUserChange = { email = it },
        onPassChange = { pass = it },
        onLoginClick = {
            viewModel.login(email, pass,
                onSuccess = {
                    Toast.makeText(context, "Login OK", Toast.LENGTH_SHORT).show()
                    nav.navigate(Route.Home.path)
                },
                onFail = {
                    Toast.makeText(context, it, Toast.LENGTH_LONG).show()
                }
            )
        },
        onRegisterClick = { nav.navigate(Route.Register.path) }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    AplicacionesIOTTheme {
        LoginContent(
            user = "javier@demo.cl",
            pass = "123456",
            onUserChange = {},
            onPassChange = {},
            onLoginClick = {},
            onRegisterClick = {}
        )
    }
}

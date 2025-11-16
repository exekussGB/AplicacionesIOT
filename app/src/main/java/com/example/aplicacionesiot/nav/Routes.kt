package com.example.aplicacionesiot.nav

sealed class Route(val path: String) {
    data object Login : Route("login")
    data object Register : Route("register")
    data object Home : Route("home")
}
package com.example.myapplication.ui.navigation

sealed class Screens (val  screen: String){
    data object Home: Screens("home")
    data object Game: Screens("game")
    data object Team: Screens("team")
    data object Profile: Screens("profile")
}


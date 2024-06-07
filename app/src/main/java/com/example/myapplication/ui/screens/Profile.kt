package com.example.myapplication.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.GreenJC
import androidx.compose.ui.platform.LocalContext



@Composable
fun Profile() {
    val context = LocalContext.current
    val (wins, losses) = loadUserStats(context)

    Column(modifier = Modifier.fillMaxSize().background(Color.Black).padding(top = 50.dp)) {
        Text(
            text = "User profile",
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp),
            color = Color.White,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Number of games: ${wins+losses}",
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp),
            color = GreenJC
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Number of wins: ${wins}",
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp),
            color = GreenJC
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Number of losses: ${losses}",
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp),
            color = GreenJC
        )

    }
}

fun loadUserStats(context: Context): Pair<Int, Int> {
    val sharedPreferences = context.getSharedPreferences("GameStats", Context.MODE_PRIVATE)
    val wins = sharedPreferences.getInt("Wins", 0)
    val losses = sharedPreferences.getInt("Losses", 0)
    return Pair(wins, losses)
}

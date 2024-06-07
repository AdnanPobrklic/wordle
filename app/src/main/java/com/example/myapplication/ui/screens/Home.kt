package com.example.myapplication.ui.screens

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

@Composable
fun Home(navController: NavController) {
    val showTut = remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(
            text = "Wordle game",
            color = GreenJC,
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 40.dp)
        )

        if (showTut.value == 0) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                Button(
                    onClick = {
                        navController.navigate("game")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = GreenJC)
                ) {
                    Text(text = "Start game")
                }

                Spacer(modifier = Modifier.height(50.dp))

                Button(
                    onClick = { showTut.value = 1 },
                    colors = ButtonDefaults.buttonColors(containerColor = GreenJC)
                ) {
                    Text(text = "Game rules")
                }
            }
        } else {
            Column(modifier = Modifier.align(Alignment.Center).padding(start = 25.dp)) {

                Text(
                    text = "The Wordle game rules are simple: guess the five-letter word within six attempts. " +
                            "Each guess must be a valid word. After each guess, the color of the tiles changes to show how close your guess was to the word. " +
                            "Green indicates correct letters in the correct positions, cyan indicates correct letters in the wrong positions, and gray indicates incorrect letters.",
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp),
                    color = GreenJC
                )
                Spacer(modifier = Modifier.height(40.dp))

                Button(onClick = { showTut.value = 0 }, colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(text = "Back to main", color = GreenJC, textDecoration = TextDecoration.Underline)

                }
            }
        }
    }
}

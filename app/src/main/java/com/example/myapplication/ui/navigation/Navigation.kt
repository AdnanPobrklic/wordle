package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.Screens
import com.example.myapplication.ui.screens.Game
import com.example.myapplication.ui.screens.Home
import com.example.myapplication.ui.screens.Team
import com.example.myapplication.ui.screens.Profile
import com.example.myapplication.ui.theme.GreenJC
import com.example.myapplication.ui.theme.MyApplicationTheme

fun onShareClick(context: Context) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, "Podeli ovu aplikaciju sa svojim prijateljima!")
    context.startActivity(Intent.createChooser(intent, "Podeli preko..."))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
        .background(GreenJC)
        .fillMaxWidth()
) {
    val context = LocalContext.current
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = GreenJC
        ),
        title = { Text(text = "Wordle Game", color = Color.White) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Nazad", tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = {
                onShareClick(context)
            }) {
                Icon(Icons.Default.Share, contentDescription ="share", tint = Color.White )
            }
        },
        modifier = modifier
    )
}

@Composable
public fun MyApp() {
    val navigationController = rememberNavController()
    val currentScreen = remember { mutableStateOf(Screens.Home.screen) }
    val previousScreen = remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            MyTopAppBar(
                onBackClick = {
                    if (!navigationController.popBackStack()) {
                        previousScreen.value?.let {
                            navigationController.navigate(it) {
                                popUpTo(navigationController.graph.startDestinationId) { inclusive = false }
                            }
                            previousScreen.value = null
                        }
                    }
                })
        },
        bottomBar = {
            BottomAppBar(containerColor = GreenJC) {
                IconButton(
                    onClick = {
                        previousScreen.value = currentScreen.value
                        currentScreen.value = Screens.Home.screen
                        navigationController.navigate(Screens.Home.screen) {
                            popUpTo(navigationController.graph.startDestinationId) { inclusive = false }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Home, contentDescription = null, modifier = Modifier.size(26.dp),
                        tint = if (currentScreen.value == Screens.Home.screen) Color.White else Color.DarkGray
                    )
                }

                IconButton(
                    onClick = {
                        previousScreen.value = currentScreen.value
                        currentScreen.value = Screens.Game.screen
                        navigationController.navigate(Screens.Game.screen) {
                            popUpTo(navigationController.graph.startDestinationId) { inclusive = false }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.PlayArrow, contentDescription = null, modifier = Modifier.size(26.dp),
                        tint = if (currentScreen.value == Screens.Game.screen) Color.White else Color.DarkGray
                    )
                }

                IconButton(
                    onClick = {
                        previousScreen.value = currentScreen.value
                        currentScreen.value = Screens.Team.screen
                        navigationController.navigate(Screens.Team.screen) {
                            popUpTo(navigationController.graph.startDestinationId) { inclusive = false }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Build, contentDescription = null, modifier = Modifier.size(26.dp),
                        tint = if (currentScreen.value == Screens.Team.screen) Color.White else Color.DarkGray
                    )
                }

                IconButton(
                    onClick = {
                        previousScreen.value = currentScreen.value
                        currentScreen.value = Screens.Team.screen
                        navigationController.navigate(Screens.Profile.screen) {
                            popUpTo(navigationController.graph.startDestinationId) { inclusive = false }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        Icons.Default.Person, contentDescription = null, modifier = Modifier.size(26.dp),
                        tint = if (currentScreen.value == Screens.Profile.screen) Color.White else Color.DarkGray
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navigationController, startDestination = Screens.Home.screen,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.Home.screen) {
                currentScreen.value = Screens.Home.screen
                Home(navController = navigationController)
            }
            composable(Screens.Game.screen) {
                currentScreen.value = Screens.Game.screen
                Game()
            }
            composable(Screens.Team.screen) {
                currentScreen.value = Screens.Team.screen
                Team()
            }
            composable(Screens.Profile.screen) {
                currentScreen.value = Screens.Profile.screen
                Profile()
            }
        }

    }
}


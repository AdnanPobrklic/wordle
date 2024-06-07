package com.example.myapplication.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.MyViewModel
import com.example.myapplication.ui.components.Keyboard
import com.example.myapplication.ui.components.LetterButton
import com.example.myapplication.ui.theme.GreenJC
import kotlinx.coroutines.delay

@Composable
fun Game(viewModel: MyViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val data = viewModel.wordData.observeAsState().value
    val tryData = remember { mutableStateOf("") }
    val message = remember { mutableStateOf("") }

    val firstRow = listOf('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p')
    val secondRow = listOf('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l')
    val thirdRow = listOf('z', 'x', 'c', 'v', 'b', 'n', 'm')

    val correctPositions = remember { mutableStateOf(0) }
    val correctLetters = remember { mutableStateOf(0) }
    val correctLetterExistsPositions = rememberSaveable { mutableListOf<Char>() }
    val incorrectLetterExistsPosition = rememberSaveable { mutableListOf<Char>() }
    val guessesWords = rememberSaveable { mutableListOf<String>() }
    val showRestartScreen = rememberSaveable { mutableStateOf(false) }
    val attemptCount = remember { mutableStateOf(0) }
    val context = LocalContext.current
    val restartMessage = remember { mutableStateOf("") }

    val (currentWins, currentLosses) = loadGameStats(context)


    Box(modifier = Modifier
        .fillMaxSize()
        .then(if (!showRestartScreen.value) Modifier.padding(top = 40.dp) else Modifier.padding(top = 0.dp))
    )
        {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(35.dp))
                Column {
                    for (word in guessesWords) {
                        Row {
                            for (l in word.indices) {
                                LetterButton(
                                    letter = word[l],
                                    onClick = { /*TODO*/ },
                                    backgroundColor = if (word[l] == data?.get(l)) Color.Green else if (data != null && data.contains(word[l])) Color.Cyan else Color.DarkGray
                                )
                            }
                        }
                    }

                    val emptyRows = 6 - guessesWords.size
                    for (i in 1..emptyRows) {
                        Row {
                            for (j in 1..5) {
                                LetterButton(
                                    letter = ' ',
                                    onClick = { /*TODO*/ },
                                    backgroundColor = Color.DarkGray
                                )
                            }
                        }
                    }
                }

            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = tryData.value, modifier = Modifier.width(150.dp), textAlign = TextAlign.Center)
                    if (tryData.value.isNotEmpty()) {
                        IconButton(onClick = {
                            tryData.value = tryData.value.dropLast(1)
                        }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Delete")
                        }
                    }
                }

                Text(text = message.value, fontSize = 18.sp, color = Color.Red)

                Keyboard(
                    firstRow = firstRow,
                    secondRow = secondRow,
                    thirdRow = thirdRow,
                    onLetterClick = { clickedLetter ->
                        message.value = ""
                        if (tryData.value.length < 5) {
                            tryData.value += clickedLetter

                            if (tryData.value.length == 5) {
                                attemptCount.value++
                                viewModel.checkWord(tryData.value) { isValid ->
                                    if (isValid) {
                                        if (tryData.value == data) {
                                            val (wins, losses) = loadGameStats(context)
                                            saveGameStats(context, wins + 1, losses)
                                            guessesWords.add(tryData.value)
                                            restartMessage.value = "Čestitamo! Pogodili ste reč iz ${attemptCount.value} pokušaja!"
                                            showRestartScreen.value = true
                                        } else {
                                            val (wins, losses) = loadGameStats(context)
                                            if (attemptCount.value == 6) {
                                                saveGameStats(context, wins, losses + 1)
                                                restartMessage.value = "Nažalost niste pogodili! Skrivena rijec je bila ${data}! Pokušajte ponovo novu riječ!"
                                                showRestartScreen.value = true
                                            }
                                            correctPositions.value = 0
                                            correctLetters.value = 0
                                            incorrectLetterExistsPosition.clear()
                                            correctLetterExistsPositions.clear()

                                            for (i in tryData.value.indices) {
                                                if (tryData.value[i] == data?.get(i)) {
                                                    correctPositions.value++
                                                    correctLetters.value++
                                                    correctLetterExistsPositions.add(tryData.value[i])
                                                } else if (data != null && data.contains(tryData.value[i])) {
                                                    correctLetters.value++
                                                    incorrectLetterExistsPosition.add(tryData.value[i])
                                                }
                                            }
                                            guessesWords.add(tryData.value)
                                        }
                                    } else {
                                        message.value = "Riječ ne postoji!"
                                    }
                                    tryData.value = ""
                                }
                            }
                        }
                    },
                    correctPositions = correctLetterExistsPositions
                )
            }
        }

        if (showRestartScreen.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xAA000000))
                    .align(Alignment.Center)
                    .padding(bottom = 150.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = restartMessage.value,
                        fontSize = 20.sp,
                        color = GreenJC,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(onClick = { onShareClick(context = context, rijec = data.toString(), pokusaji = attemptCount.value) }, colors = ButtonDefaults.buttonColors(containerColor = GreenJC)) {
                        Text(text = "Podjeli rezultat")
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    Button(onClick = {
                        viewModel.fetchNewWord()
                        guessesWords.clear()
                        tryData.value = ""
                        message.value = ""
                        correctPositions.value = 0
                        correctLetters.value = 0
                        correctLetterExistsPositions.clear()
                        incorrectLetterExistsPosition.clear()
                        showRestartScreen.value = false
                        attemptCount.value = 0
                    }, colors = ButtonDefaults.buttonColors(containerColor = GreenJC)) {
                        Text("Restartuj igru")
                    }
                }
            }
        }
    }
}

fun onShareClick(context: Context, rijec: String, pokusaji: Int) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, "Woohoooo, skrivena riječ je bila ${rijec} i pogodio sam je u ${pokusaji} pokušaja!")
    context.startActivity(Intent.createChooser(intent, "Podeli preko..."))
}

fun saveGameStats(context: Context, wins: Int, losses: Int) {
    val sharedPreferences = context.getSharedPreferences("GameStats", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putInt("Wins", wins)
        putInt("Losses", losses)
        apply()
    }
}

fun loadGameStats(context: Context): Pair<Int, Int> {
    val sharedPreferences = context.getSharedPreferences("GameStats", Context.MODE_PRIVATE)
    val wins = sharedPreferences.getInt("Wins", 0)
    val losses = sharedPreferences.getInt("Losses", 0)
    return Pair(wins, losses)
}

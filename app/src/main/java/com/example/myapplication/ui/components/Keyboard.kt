package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Keyboard(
    firstRow: List<Char>,
    secondRow: List<Char>,
    thirdRow: List<Char>,
    onLetterClick: (Char) -> Unit,
    correctPositions: List<Char>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (letter in firstRow) {
                val backgroundColor = if (letter in correctPositions) Color.Green else Color.DarkGray
                LetterButton(
                    letter = letter,
                    onClick = { onLetterClick(letter) },
                    backgroundColor = backgroundColor
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (letter in secondRow) {
                val backgroundColor = if (letter in correctPositions) Color.Green else Color.DarkGray
                LetterButton(
                    letter = letter,
                    onClick = { onLetterClick(letter) },
                    backgroundColor = backgroundColor
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (letter in thirdRow) {
                val backgroundColor = if (letter in correctPositions) Color.Green else Color.DarkGray
                LetterButton(
                    letter = letter,
                    onClick = { onLetterClick(letter) },
                    backgroundColor = backgroundColor
                )
            }
        }
    }
}

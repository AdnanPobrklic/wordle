package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.GreenJC

@Composable
fun Team() {
    Box (modifier = Modifier.fillMaxSize()){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .padding(top = 30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row {
                Text(text = "Mobile Development", color = GreenJC, textAlign = TextAlign.Center,
                    fontSize = 25.sp,   modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .size(30.dp)
                        .padding(bottom = 10.dp, top = 10.dp)
                )
            }

            Row (modifier = Modifier.padding(top=30.dp, bottom = 30.dp)) {
                Text(text = "Hackaton", fontSize = 25.sp, color = GreenJC)
            }



            Column (verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                ) {

                Row {
                    Text(text = "Project: Wordle Game", modifier = Modifier.padding(bottom = 15.dp))
                }

                Column {
                    Row {
                        Text(text = "Professor: prof.dr. Selmanović Elmedin", modifier = Modifier.padding(bottom = 10.dp))
                    }
                    Row {
                        Text(text = "Students:", modifier = Modifier.padding(bottom = 10.dp))
                    }
                    Row (modifier = Modifier.padding(start = 20.dp), verticalAlignment = Alignment.Bottom) {
                        Icon(Icons.Default.Person, contentDescription = "MB" )
                        Text(text = "Muratović Belmin", modifier = Modifier.padding(bottom = 5.dp))
                    }
                    Row (modifier= Modifier.padding(start=20.dp), verticalAlignment = Alignment.Bottom) {
                        Icon(Icons.Default.Person, contentDescription = "PA" )
                        Text(text = "Pobrklić Adnan")
                    }
                }

            }            





        }
    }
}
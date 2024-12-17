package com.example.layoutcomposables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize(), // Modifier 추가
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Text(
            text = "Hello Kotlin!",
            modifier = modifier
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Green)
                .padding(10.dp)
        ) {
            Icon(
                Icons.Default.Home,
                contentDescription = null,
                modifier = modifier.size(40.dp)
            )
            Text(
                text = "Home"
            )
        }
        Box(
            modifier = modifier
                .background(Color.Yellow)
                .size(200.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Icon(
                Icons.Default.Home,
                contentDescription = null,
                modifier = modifier.size(40.dp)
            )
            Icon(
                Icons.Default.Delete,
                contentDescription = null,
                modifier = modifier.size(40.dp).align(Alignment.Center)
            )
        }
    }
}

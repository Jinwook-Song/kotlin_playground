package com.example.counterapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CounterApp(modifier: Modifier = Modifier) {
    val counter: MutableState<Int> = remember {
        mutableStateOf(0)
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Log.d("CounterApp", "CounterApp recomposed")
        Text(text = "You have pushed the button this many times")
        Text(text = "${counter.value}", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                Log.d("CounterApp", "CounterApp: ${counter.value}")
                counter.value++
            },
        ) {
            Text("Click me")
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ConterAppPreview() {
    CounterApp()
}
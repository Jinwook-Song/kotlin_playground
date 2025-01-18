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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.counterapp.viewmodels.CounterViewModel


@Composable
fun CounterApp(modifier: Modifier = Modifier) {
    val counterViewModel: CounterViewModel = viewModel()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Log.d("CounterApp", "CounterApp recomposed")
        Text(text = "You have pushed the button this many times")
        Text(text = "${counterViewModel.counter}", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                Log.d("CounterApp", "CounterApp: ${counterViewModel.counter}")
                counterViewModel.increaseCounter()
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
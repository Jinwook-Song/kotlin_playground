package com.example.counterapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.counterapp.MyApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiCalculator(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Bmi Calculator")
                }
            )
        }
    ) {
        val weight = remember { mutableStateOf("") }
        Column(
            modifier = modifier.padding(it)
        ) {
            EditNumberField(
                value = weight.value,
                label = "Weight(in kg)",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                onValueChange = { weight.value = it },
            )

        }
    }
}

@Composable
fun EditNumberField(
    value: String,
    label: String,
    keyboardOptions: KeyboardOptions,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        label = { Text(label) },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}


@Preview
@Composable
private fun BmiCalculatorPrev() {
    MyApp()
}
package com.example.counterapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.counterapp.MyApp
import com.example.counterapp.viewmodels.BmiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BmiCalculator(modifier: Modifier = Modifier) {
    val bmiViewModel: BmiViewModel = viewModel()
    val state = bmiViewModel.bmiState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Bmi Calculator")
                }
            )
        }
    ) {
        Column(
            modifier = modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EditNumberField(
                value = state.weight,
                label = "Weight(in kg)",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                onValueChange = bmiViewModel::updateWeight
            )
            EditNumberField(
                value = state.height,
                label = "Height(in meter)",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                onValueChange = bmiViewModel::updateHeight
            )
            Button(
                onClick = {
                    bmiViewModel.calculateBmi()
                }
            ) {
                Text("Calculate")
            }
            BmiResult(
                bmi = state.bmi,
                status = state.status,
                statusMap = BmiViewModel.statusMap,
                modifier = Modifier.weight(1f),
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

@Composable
fun BmiResult(
    bmi: String,
    status: String,
    modifier: Modifier = Modifier,
    statusMap: Map<String, String>,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "BMI: $bmi",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        if (status.isNotBlank()) {
            for (key in statusMap.keys) {
                val isMatched = status == key
                val color = if (isMatched) Color.LightGray else Color.Transparent
                val fontWeight = if (isMatched) FontWeight.Bold else FontWeight.Normal
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                        .fillMaxWidth()
                        .background(color)
                ) {
                    Text(text = key, fontWeight = fontWeight, modifier = Modifier.weight(1f))
                    Text(text = statusMap[key]!!, fontWeight = fontWeight)

                }
            }
        }
    }

}


@Preview
@Composable
private fun BmiCalculatorPrev() {
    MyApp()
}


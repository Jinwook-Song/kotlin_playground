package com.example.counterapp.screens

import android.annotation.SuppressLint
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
    ) { it ->
        val weight = remember { mutableStateOf("") }
        val height = remember { mutableStateOf("") }
        val bmi = remember { mutableStateOf("") }
        val status = remember { mutableStateOf("") }
        Column(
            modifier = modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EditNumberField(
                value = weight.value,
                label = "Weight(in kg)",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                onValueChange = { weight.value = it },
            )
            EditNumberField(
                value = height.value,
                label = "Height(in meter)",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                onValueChange = { height.value = it },
            )
            Button(
                onClick = {
                    bmi.value = calculateBmi(
                        weight.value.toDoubleOrNull() ?: 0.0,
                        height.value.toDoubleOrNull() ?: 0.0
                    )
                    status.value = getStatus(bmi.value.toDoubleOrNull() ?: 0.0)
                }
            ) {
                Text("Calculate")
            }
            BmiResult(
                bmi = bmi.value,
                status = status.value,
                modifier = Modifier.weight(1f)
            )

        }
    }
}

@SuppressLint("DefaultLocale")
fun calculateBmi(weight: Double, height: Double): String {
    val bmi = weight / (height * height)
    return String.format("%.1f", bmi)
}

fun getStatus(bmi: Double): String {
    return when {
        bmi < 16.0 -> BMIStatus.UNDERWEIGHT_SEVERE
        bmi >= 16.0 && bmi < 17.0 -> BMIStatus.UNDERWEIGHT_MODERATE
        bmi >= 17.0 && bmi < 18.5 -> BMIStatus.UNDERWEIGHT
        bmi >= 18.5 && bmi < 25.0 -> BMIStatus.NORMAL
        bmi >= 25.0 && bmi < 30.0 -> BMIStatus.OVERWEIGHT
        bmi >= 30.0 && bmi < 35.0 -> BMIStatus.OBESE_CLASS_I
        bmi >= 35.0 && bmi < 40.0 -> BMIStatus.OBESE_CLASS_II
        else -> BMIStatus.OBESE_CLASS_III
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

object BMIStatus {
    const val UNDERWEIGHT_SEVERE = "Underweight (Severe thinness)"
    const val UNDERWEIGHT_MODERATE = "Underweight (Moderate thinness)"
    const val UNDERWEIGHT = "Underweight (Mild thinness)"
    const val NORMAL = "Normal"
    const val OVERWEIGHT = "Overweight (Pre-obese)"
    const val OBESE_CLASS_I = "Obese (Class I)"
    const val OBESE_CLASS_II = "Obese (Class II)"
    const val OBESE_CLASS_III = "Obese (Class III)"
}

val statusMap = mapOf(
    BMIStatus.UNDERWEIGHT_SEVERE to "Less than 16.0",
    BMIStatus.UNDERWEIGHT_MODERATE to "16.0 - 16.9",
    BMIStatus.UNDERWEIGHT to "17.0 - 18.4",
    BMIStatus.NORMAL to "18.5 - 24.9",
    BMIStatus.OVERWEIGHT to "25.0 - 29.9",
    BMIStatus.OBESE_CLASS_I to "30.0 - 34.9",
    BMIStatus.OBESE_CLASS_II to "35.0 - 39.9",
    BMIStatus.OBESE_CLASS_III to "40 and above"
)
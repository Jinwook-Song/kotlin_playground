package com.example.counterapp.viewmodels

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BmiViewModel : ViewModel() {
    val bmiState = mutableStateOf(BmiState())

    fun updateWeight(weight: String) {
        bmiState.value = bmiState.value.copy(
            weight = weight
        )
        
    }

    fun updateHeight(height: String) {
        bmiState.value = bmiState.value.copy(
            height = height
        )
    }

    @SuppressLint("DefaultLocale")
    fun calculateBmi() {
        val weight = bmiState.value.weight.toDoubleOrNull() ?: 0.0
        val height = bmiState.value.height.toDoubleOrNull() ?: 0.0
        val bmiValue = weight / (height * height)
        bmiState.value = bmiState.value.copy(
            bmi = String.format("%.1f", bmiValue),
            status = getStatus(bmiValue)
        )
    }

    private fun getStatus(bmi: Double): String {
        return when {
            bmi < 16.0 -> UNDERWEIGHT_SEVERE
            bmi >= 16.0 && bmi < 17.0 -> UNDERWEIGHT_MODERATE
            bmi >= 17.0 && bmi < 18.5 -> UNDERWEIGHT
            bmi >= 18.5 && bmi < 25.0 -> NORMAL
            bmi >= 25.0 && bmi < 30.0 -> OVERWEIGHT
            bmi >= 30.0 && bmi < 35.0 -> OBESE_CLASS_I
            bmi >= 35.0 && bmi < 40.0 -> OBESE_CLASS_II
            else -> OBESE_CLASS_III
        }
    }


    // static member
    companion object {
        const val UNDERWEIGHT_SEVERE = "Underweight (Severe thinness)"
        const val UNDERWEIGHT_MODERATE = "Underweight (Moderate thinness)"
        const val UNDERWEIGHT = "Underweight (Mild thinness)"
        const val NORMAL = "Normal"
        const val OVERWEIGHT = "Overweight (Pre-obese)"
        const val OBESE_CLASS_I = "Obese (Class I)"
        const val OBESE_CLASS_II = "Obese (Class II)"
        const val OBESE_CLASS_III = "Obese (Class III)"

        val statusMap = mapOf(
            UNDERWEIGHT_SEVERE to "Less than 16.0",
            UNDERWEIGHT_MODERATE to "16.0 - 16.9",
            UNDERWEIGHT to "17.0 - 18.4",
            NORMAL to "18.5 - 24.9",
            OVERWEIGHT to "25.0 - 29.9",
            OBESE_CLASS_I to "30.0 - 34.9",
            OBESE_CLASS_II to "35.0 - 39.9",
            OBESE_CLASS_III to "40 and above"
        )
    }
}

data class BmiState(
    val weight: String = "",
    val height: String = "",
    val bmi: String = "",
    val status: String = "",
)
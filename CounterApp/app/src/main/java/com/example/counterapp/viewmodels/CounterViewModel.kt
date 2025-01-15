package com.example.counterapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    var counter by mutableIntStateOf(0)

    fun increaseCounter() {
        counter++
    }


}
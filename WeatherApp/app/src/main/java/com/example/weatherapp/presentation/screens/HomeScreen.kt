package com.example.weatherapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.weatherapp.R
import com.example.weatherapp.presentation.widgets.AppBackground
import com.example.weatherapp.presentation.widgets.WeatherSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: WeatherHomeState,
    modifier: Modifier = Modifier,
) {


    Box(modifier = modifier.fillMaxSize()) {
        AppBackground(photoId = R.drawable.weather_bg)
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Weather App", style = MaterialTheme.typography.titleLarge)
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        actionIconContentColor = Color.White
                    )
                )
            },
            containerColor = Color.Transparent
        ) {
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .wrapContentSize()
            ) {
                when (uiState) {
                    is WeatherHomeState.Error ->
                        Text("Fail to fetch data", style = MaterialTheme.typography.displaySmall)

                    is WeatherHomeState.Loading -> CircularProgressIndicator()
                    is WeatherHomeState.Success -> WeatherSection(uiState.weather)

                }

            }
        }

    }
}
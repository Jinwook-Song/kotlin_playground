package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.presentation.screens.HomeScreen
import com.example.weatherapp.presentation.screens.HomeViewModel
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherApp()
        }
    }
}


@Composable
fun WeatherApp(modifier: Modifier = Modifier) {
    val homeViewModel: HomeViewModel = viewModel()
    homeViewModel.getWeatherData()

    WeatherAppTheme {
        HomeScreen(modifier)
    }
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    WeatherApp()
}
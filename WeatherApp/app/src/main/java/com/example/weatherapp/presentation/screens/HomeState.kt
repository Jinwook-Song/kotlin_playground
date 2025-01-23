package com.example.weatherapp.presentation.screens

import com.example.weatherapp.data.CurrentWeather
import com.example.weatherapp.data.ForecastWeather

data class Weather(
    val currentWeather: CurrentWeather,
    val forecastWeather: ForecastWeather
)

sealed interface WeatherHomeState {
    data class Success(val weather: Weather) : WeatherHomeState
    data object Error : WeatherHomeState
    data object Loading : WeatherHomeState
}
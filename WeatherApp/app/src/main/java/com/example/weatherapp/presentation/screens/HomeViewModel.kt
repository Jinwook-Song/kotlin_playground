package com.example.weatherapp.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.CurrentWeather
import com.example.weatherapp.data.ForecastWeather
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.WeatherRepositoryImpl
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl()

    var uiState: WeatherHomeState by mutableStateOf(WeatherHomeState.Loading)

    fun getWeatherData() {
        viewModelScope.launch {
            uiState = try {
                val currentWeather = async { getCurrentData() }.await()
                val forecastWeather = async { getForecastData() }.await()

                WeatherHomeState.Success(Weather(currentWeather, forecastWeather))

            } catch (e: Exception) {
                WeatherHomeState.Error
            }
        }
    }

    private suspend fun getCurrentData(): CurrentWeather {
        val endUrl =
            "weather?lat=37.5252012&lon=127.0263883&appid=8ab63e31b6ca6a84e4f0a055bdffc9a4&units=metric"
        return weatherRepository.getCurrentWeather(endUrl)
    }

    private suspend fun getForecastData(): ForecastWeather {
        val endUrl =
            "forecast?lat=37.5252012&lon=127.0263883&appid=8ab63e31b6ca6a84e4f0a055bdffc9a4&units=metric"
        return weatherRepository.getForecastWeather(endUrl)
    }


}
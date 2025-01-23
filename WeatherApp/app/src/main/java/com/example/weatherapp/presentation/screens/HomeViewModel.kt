package com.example.weatherapp.presentation.screens

import android.util.Log
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

    fun getWeatherData() {
        viewModelScope.launch {
            try {
                val currentWeather = async { getCurrentData() }.await()
                val forecastWeather = async { getForecastData() }.await()

                Log.d("HomeViewModel", "currentWeather: ${currentWeather.main!!.temp}")
                Log.d("HomeViewModel", "forecastWeather: ${forecastWeather.list!!.size}")

            } catch (e: Exception) {

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
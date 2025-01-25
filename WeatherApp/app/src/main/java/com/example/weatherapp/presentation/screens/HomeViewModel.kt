package com.example.weatherapp.presentation.screens

import android.app.Application
import android.net.ConnectivityManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.weatherapp.data.ConnectivityRepository
import com.example.weatherapp.data.ConnectivityRepositoryImpl
import com.example.weatherapp.data.CurrentWeather
import com.example.weatherapp.data.ForecastWeather
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.WeatherRepositoryImpl
import com.example.weatherapp.utils.EnvConfig
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val connectionRepository: ConnectivityRepository,
) : ViewModel(
) {
    private val weatherRepository: WeatherRepository = WeatherRepositoryImpl()
    private val appId = EnvConfig.WEATHER_API_KEY

    var uiState: WeatherHomeState by mutableStateOf(WeatherHomeState.Loading)

    // Error propagation
    private val exceptionHandler =
        CoroutineExceptionHandler { _: CoroutineContext, _: Throwable ->
            uiState = WeatherHomeState.Error
        }

    private var latitude = 0.0
    private var longitude = 0.0
    val connectivityState: StateFlow<ConnectivityState> = connectionRepository.connectivityState

    fun setLocation(lat: Double, lon: Double) {
        latitude = lat
        longitude = lon
    }

    fun getWeatherData() {
        viewModelScope.launch(exceptionHandler) {
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
            "weather?lat=${latitude}&lon=${longitude}&appid=${appId}&units=metric"
        return weatherRepository.getCurrentWeather(endUrl)
    }

    private suspend fun getForecastData(): ForecastWeather {
        val endUrl =
            "forecast?lat=${latitude}&lon=${longitude}&appid=${appId}&units=metric"
        return weatherRepository.getForecastWeather(endUrl)
    }

    // Define ViewModel factory in a companion object
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as Application
                val connectivityManager =
                    application.getSystemService(ConnectivityManager::class.java)
                HomeViewModel(
                    connectionRepository = ConnectivityRepositoryImpl(connectivityManager)
                )

            }
        }
    }


}
package com.example.weatherapp.utils

import io.github.cdimascio.dotenv.dotenv

object EnvConfig {
    private val dotenv = dotenv {
        directory = "/assets"
        filename = "env"
    }

    val WEATHER_API_KEY: String = dotenv["WEATHER_API_KEY"]
        ?: throw IllegalStateException("WEATHER_API_KEY is not set in the environment")
}
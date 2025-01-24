package com.example.weatherapp.presentation.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.weatherapp.data.CurrentWeather
import com.example.weatherapp.data.ForecastWeather
import com.example.weatherapp.presentation.screens.Weather
import com.example.weatherapp.utils.degree
import com.example.weatherapp.utils.formatDateFromInt
import com.example.weatherapp.utils.getIconUrl

@Composable
fun WeatherSection(
    weather: Weather,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(8.dp)) {
        CurrentWeatherSection(
            currentWeather = weather.currentWeather,
            modifier = Modifier.weight(1f)
        )
        ForecastWeatherSection(forecastList = weather.forecastWeather.list!!)
    }

}

@Composable
fun CurrentWeatherSection(
    currentWeather: CurrentWeather,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "${currentWeather.name}, ${currentWeather.sys?.country}",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            formatDateFromInt(currentWeather.dt!!),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "${currentWeather.main!!.temp}${degree}",
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "feels like ${currentWeather.main.feelsLike}${degree}",
            style = MaterialTheme.typography.titleMedium
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(getIconUrl(currentWeather.weather!![0]!!.icon!!))
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Text(
                currentWeather.weather.get(0)!!.description!!,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    "Humidity ${currentWeather.main.humidity}%",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "Pressure ${currentWeather.main.pressure}hPa",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "Visibility ${currentWeather.visibility}km",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Surface(
                modifier = Modifier
                    .width(2.dp)
                    .height(100.dp)
            ) { }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    "Sunrise ${
                        formatDateFromInt(
                            currentWeather.sys!!.sunrise!!,
                            pattern = "HH:mm"
                        )
                    }",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    "Sunset ${
                        formatDateFromInt(
                            currentWeather.sys.sunset!!,
                            pattern = "HH:mm",
                        )
                    }",
                    style = MaterialTheme.typography.titleMedium
                )

            }

        }


    }

}


@Composable
fun ForecastWeatherSection(
    forecastList: List<ForecastWeather.ForecaseItem?>,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(forecastList.size) { it: Int ->
            ForecastWeatherItem(item = forecastList[it]!!)
        }
    }

}


@Composable
fun ForecastWeatherItem(
    item: ForecastWeather.ForecaseItem,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = .5f),
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 3.dp)
        ) {
            Text(
                formatDateFromInt(item.dt!!, pattern = "EEE"),
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                formatDateFromInt(item.dt, pattern = "HH:mm"),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(10.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(getIconUrl(item.weather!![0]!!.icon!!))
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(vertical = 4.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                "${item.main!!.temp!!.toInt()}${degree}",
                style = MaterialTheme.typography.titleMedium
            )

        }
    }
}

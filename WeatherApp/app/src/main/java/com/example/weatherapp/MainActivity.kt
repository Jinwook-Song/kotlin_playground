package com.example.weatherapp

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.presentation.screens.ConnectivityState
import com.example.weatherapp.presentation.screens.HomeScreen
import com.example.weatherapp.presentation.screens.HomeViewModel
import com.example.weatherapp.presentation.screens.WeatherHomeState
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)
        enableEdgeToEdge()
        setContent {
            WeatherApp(client)
        }
    }
}


@Composable
fun WeatherApp(
    client: FusedLocationProviderClient,
    modifier: Modifier = Modifier,
) {
    val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
    val context = LocalContext.current
    var permissionGranted by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted: Boolean -> permissionGranted = granted }

    LaunchedEffect(Unit) {
        permissionGranted = ContextCompat.checkSelfPermission(
            context, android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!permissionGranted) launcher.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    LaunchedEffect(permissionGranted) {
        if (permissionGranted) {
            client.lastLocation.addOnSuccessListener {
                homeViewModel.setLocation(it.latitude, it.longitude)
                homeViewModel.getWeatherData()
            }
        }
    }
    val connectivityState by homeViewModel.connectivityState.collectAsState()
    WeatherAppTheme {
        HomeScreen(
            isConnected = connectivityState == ConnectivityState.Available,
            onRefresh = {
                homeViewModel.uiState = WeatherHomeState.Loading
                homeViewModel.getWeatherData()
            },
            homeViewModel.uiState,
            modifier,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    WeatherApp(
        client = TODO()
    )
}
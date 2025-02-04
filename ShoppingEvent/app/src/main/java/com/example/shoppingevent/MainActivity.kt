package com.example.shoppingevent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppingevent.destinations.AddEventRoute
import com.example.shoppingevent.destinations.EventDetailsRoute
import com.example.shoppingevent.destinations.HomeRoute
import com.example.shoppingevent.ui.addevent.AddEventPage
import com.example.shoppingevent.ui.eventdetails.EventDetailsPage
import com.example.shoppingevent.ui.home.HomePage
import com.example.shoppingevent.ui.theme.ShoppingEventTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingApp()
        }
    }
}

@Composable
fun ShoppingApp(modifier: Modifier = Modifier) {
    ShoppingEventTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = HomeRoute
        ) {
            composable<HomeRoute>() {
                HomePage(
                    navigateToAddEvent = { navController.navigate(route = AddEventRoute) },
                    modifier = modifier
                )
            }
            composable<AddEventRoute>() {
                AddEventPage(
                    navigateBack = { navController.popBackStack() },
                    navigateUp = { navController.navigateUp() },
                    modifier = modifier
                )
            }
            composable<EventDetailsRoute>() {
                EventDetailsPage(
                    navigateBack = { navController.popBackStack() },
                    navigateUp = { navController.navigateUp() },
                    modifier = modifier
                )
            }

        }

    }
}



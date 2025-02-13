package com.example.shoppingevent.ui.eventdetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingevent.customcomposables.ShoppingAppBar

@Composable
fun EventDetailsPage(
    navigateBack: () -> Unit,
    navigateUp: () -> Unit,
    viewModel: EventDetailsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            ShoppingAppBar(
                title = "Event Details",
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        }
    ) {
        Text(
            text = "Event Details",
            modifier = modifier.padding(it)
        )
    }
}
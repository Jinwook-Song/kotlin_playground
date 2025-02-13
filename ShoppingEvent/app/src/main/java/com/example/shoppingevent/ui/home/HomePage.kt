package com.example.shoppingevent.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingevent.customcomposables.ShoppingAppBar
import com.example.shoppingevent.data.entities.ShoppingEvent

@Composable
fun HomePage(
    navigateToAddEvent: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.homeUiState.collectAsState()
    Scaffold(
        topBar = {
            ShoppingAppBar(
                title = "Shopping Events",
                canNavigateBack = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAddEvent
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Event")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        if (uiState.events.isEmpty()) {
            EmptyList(
                "No Events\nAdd Events to get started!",
                modifier = modifier.padding(innerPadding)
            )
            return@Scaffold
        }
        ShoppingEventList(
            shoppingEvents = uiState.events,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ShoppingEventList(
    shoppingEvents: List<ShoppingEvent>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(shoppingEvents) { event ->
            ShoppingEventItem(event)
        }
    }
}

@Composable
fun ShoppingEventItem(
    shoppingEvent: ShoppingEvent,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier,
        leadingContent = {
            IconButton(
                onClick = {}
            ) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete Event")
            }
        },
        headlineContent = {
            Text(shoppingEvent.name)
        },
        supportingContent = {
            Text(shoppingEvent.eventDate)
        },
        trailingContent = {
            Text(
                "\$ ${shoppingEvent.totalCost}",
                style = MaterialTheme.typography.bodyLarge
            )
        }

    )

}

@Composable
fun EmptyList(
    message: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        Text(message, textAlign = TextAlign.Center)
    }

}














package com.example.shoppingevent.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingevent.customcomposables.EmptyList
import com.example.shoppingevent.customcomposables.ShoppingAppBar
import com.example.shoppingevent.data.entities.ShoppingEvent
import kotlinx.coroutines.launch

@Composable
fun HomePage(
    navigateToAddEvent: () -> Unit,
    navigateToEventDetails: (Long, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.homeUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
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
            navigateToEventDetails = navigateToEventDetails,
            onDelete = {
                coroutineScope.launch {
                    viewModel.deleteEvent(it)
                }
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ShoppingEventList(
    shoppingEvents: List<ShoppingEvent>,
    navigateToEventDetails: (Long, String) -> Unit,
    onDelete: (ShoppingEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(shoppingEvents) { event ->
            ShoppingEventItem(
                event,
                onTapEvent = navigateToEventDetails,
                onDelete = onDelete
            )
        }
    }
}

@Composable
fun ShoppingEventItem(
    shoppingEvent: ShoppingEvent,
    onTapEvent: (Long, String) -> Unit,
    onDelete: (ShoppingEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier
            .padding(8.dp)
            .clickable {
                onTapEvent(shoppingEvent.id, shoppingEvent.name)
            },
        tonalElevation = 10.dp,
        leadingContent = {
            IconButton(
                onClick = {
                    onDelete(shoppingEvent)
                }
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
















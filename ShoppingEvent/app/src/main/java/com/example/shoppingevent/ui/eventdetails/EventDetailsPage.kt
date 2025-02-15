package com.example.shoppingevent.ui.eventdetails

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
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
import com.example.shoppingevent.ui.addevent.AddEventDetails
import kotlinx.coroutines.launch

@Composable
fun EventDetailsPage(
    navigateBack: () -> Unit,
    navigateUp: () -> Unit,
    viewModel: EventDetailsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = {
            ShoppingAppBar(
                title = "Event Details",
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        viewModel.addItem()
                        if (uiState.itemList.isNotEmpty()) {
                            lazyListState.animateScrollToItem(uiState.itemList.size)
                        }
                    }
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Item")
                Text(text = "Add Item")
            }
        }
    ) { innerPadding ->
        if (uiState.itemList.isEmpty()) {
            EmptyList(
                "아이템을 추가해주세요",
                modifier = modifier.padding(innerPadding)
            )
            return@Scaffold
        }

        ShoppingItemList(
            eventDetails = uiState.eventDetails,
            shoppingItems = uiState.itemList,
            lazyListState = lazyListState,
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ShoppingItemList(
    eventDetails: AddEventDetails,
    shoppingItems: List<ItemUiState>,
    lazyListState: LazyListState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        state = lazyListState,
        modifier = modifier
    ) {
        item {
            ListItem(
                colors = ListItemDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                headlineContent = {
                    Text(
                        "Budget: \$${eventDetails.initialBudget}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                trailingContent = {
                    Text(
                        "\$${eventDetails.totalCost}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer

                    )
                }
            )
        }

        items(
            items = shoppingItems,
            key = { it.itemDetails.itemId }
        ) {
            ListItem(
                headlineContent = {
                    Text(it.itemDetails.name)
                },
                supportingContent = {
                    Text("Quantity: ${it.itemDetails.quantity}")
                },
                trailingContent = {
                    Text(
                        "Price: ${it.itemDetails.price}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}
































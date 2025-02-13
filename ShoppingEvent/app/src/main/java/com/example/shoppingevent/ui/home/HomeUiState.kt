package com.example.shoppingevent.ui.home

import com.example.shoppingevent.data.entities.ShoppingEvent

data class HomeUiState(
    val events: List<ShoppingEvent> = emptyList(),
)
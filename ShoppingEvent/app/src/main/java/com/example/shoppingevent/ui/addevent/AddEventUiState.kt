package com.example.shoppingevent.ui.addevent

import com.example.shoppingevent.data.entities.ShoppingEvent

data class AddEventDetails(
    val id: Long = 0,
    val name: String = "",
    val initialBudget: String = "0.0",
    val totalCost: Double = 0.0,
    val eventDate: String = "",
    val completed: Boolean = false,
)

data class AddEventUiState(
    val addEventDetails: AddEventDetails = AddEventDetails(),
    val isEntryValid: Boolean = false,
)


// to ShoppingEvent
fun AddEventDetails.toEntity(): ShoppingEvent = ShoppingEvent(
    id = id,
    name = name,
    initialBudget = initialBudget.toDoubleOrNull() ?: 0.0,
    totalCost = totalCost,
    eventDate = eventDate,
    completed = completed
)
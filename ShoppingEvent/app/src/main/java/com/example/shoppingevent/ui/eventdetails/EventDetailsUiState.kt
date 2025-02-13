package com.example.shoppingevent.ui.eventdetails

import com.example.shoppingevent.ui.addevent.AddEventDetails

data class ItemDetails(
    val itemId: Long = 0,
    val eventId: Long = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)

data class ItemUiState(
    val isEdit: Boolean = false,
    val itemDetails: ItemDetails = ItemDetails(),
)

data class EventDetailsUiState(
    val eventDetails: AddEventDetails = AddEventDetails(),
    val itemList: List<ItemUiState> = emptyList(),
)
package com.example.shoppingevent.ui.eventdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.shoppingevent.data.entities.ShoppingItem
import com.example.shoppingevent.data.repositories.ShoppingEventRepository
import com.example.shoppingevent.data.repositories.ShoppingItemRepository
import com.example.shoppingevent.destinations.EventDetailsRoute
import com.example.shoppingevent.ui.addevent.AddEventDetails
import com.example.shoppingevent.ui.addevent.toAddEventDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val shoppingEventRepository: ShoppingEventRepository,
    private val shoppingItemRepository: ShoppingItemRepository,
) : ViewModel() {
    private val detailsRoute: EventDetailsRoute = savedStateHandle.toRoute<EventDetailsRoute>()

    private val _uiState = MutableStateFlow(EventDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            shoppingEventRepository.getEventAndItems(detailsRoute.eventId).collect { map ->
                val entry = map.entries.firstOrNull()
                _uiState.update {
                    it.copy(
                        eventDetails = entry?.key?.toAddEventDetails()
                            ?: AddEventDetails(name = detailsRoute.eventName),
                        itemList = entry?.value?.map { item ->
                            ItemUiState(
                                itemDetails = item.toItemDetails()
                            )
                        } ?: emptyList()

                    )
                }

            }
        }
    }

    suspend fun addItem(

    ) {
        val item = ShoppingItem(eventId = detailsRoute.eventId, itemName = "Item")
        shoppingItemRepository.insert(item)

    }

}
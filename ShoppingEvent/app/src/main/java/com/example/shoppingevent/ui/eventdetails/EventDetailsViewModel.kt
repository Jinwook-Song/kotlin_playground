package com.example.shoppingevent.ui.eventdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.example.shoppingevent.data.repositories.ShoppingEventRepository
import com.example.shoppingevent.data.repositories.ShoppingItemRepository
import com.example.shoppingevent.destinations.EventDetailsRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val shoppingEventRepository: ShoppingEventRepository,
    private val shoppingItemRepository: ShoppingItemRepository,
) : ViewModel() {
    private val detailsRoute: EventDetailsRoute = savedStateHandle.toRoute<EventDetailsRoute>()
}
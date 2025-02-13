package com.example.shoppingevent.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingevent.data.repositories.ShoppingEventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    val shoppingEventRepository: ShoppingEventRepository,
) : ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow() // immutable

    init {
        viewModelScope.launch {
            shoppingEventRepository.getEvents().collect { events ->
                _homeUiState.update { it ->
                    it.copy(events = events)
                }
            }
        }
    }


}
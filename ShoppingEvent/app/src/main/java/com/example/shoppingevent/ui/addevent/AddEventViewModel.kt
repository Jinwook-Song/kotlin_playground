package com.example.shoppingevent.ui.addevent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddEventViewModel @Inject constructor() : ViewModel() {
    var addEventUiState by mutableStateOf(AddEventUiState())

    fun updateUiState(addEventDetails: AddEventDetails) {
        addEventUiState = AddEventUiState(
            addEventDetails = addEventDetails,
            isEntryValid = validateInput(addEventDetails)
        )
    }

    private fun validateInput(
        addEventDetails: AddEventDetails = addEventUiState.addEventDetails,
    ): Boolean {
        return with(addEventDetails) {
            name.isNotBlank() && eventDate.isNotBlank()
        }
    }
}
@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.shoppingevent.ui.addevent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.shoppingevent.customcomposables.ShoppingAppBar
import com.example.shoppingevent.utils.formatDate
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventPage(
    navigateBack: () -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddEventViewModel = hiltViewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            ShoppingAppBar(
                title = "Add Event",
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        }
    ) {
        EventForm(
            uiState = viewModel.addEventUiState,
            onEventValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveEvent()
                    navigateBack()
                }
            },
            modifier = modifier.padding(it)
        )
    }
}

@ExperimentalMaterial3Api
@Composable
fun EventForm(
    uiState: AddEventUiState,
    onEventValueChange: (AddEventDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var openDatePickerDialog by remember {
        mutableStateOf(false)
    }
    var confirmedDate by remember { mutableStateOf<Long?>(null) }

    val datePickerState = rememberDatePickerState()

    Column(
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextInputFields(
            uiState = uiState,
            onEventValueChange = onEventValueChange,
        )
        DatePickerUi(
            shouldOpenDialog = openDatePickerDialog,
            state = datePickerState,
            onDismiss = {
                openDatePickerDialog = false
            },
            onCancel = {
                openDatePickerDialog = false
            },
            onConfirm = {
                datePickerState.selectedDateMillis?.let {
                    confirmedDate = it
                    onEventValueChange(
                        uiState.addEventDetails.copy(
                            eventDate = formatDate(ms = it)!!
                        )
                    )
                }
                openDatePickerDialog = false
            },
        )
        DatePickerButtonUi(
            confirmedDateMillis = confirmedDate,
            onClick = { openDatePickerDialog = true }
        )

        Button(
            onClick = onSaveClick,
            enabled = uiState.isEntryValid,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text("Save")
        }
    }
}


@Composable
fun TextInputFields(
    uiState: AddEventUiState,
    onEventValueChange: (AddEventDetails) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = uiState.addEventDetails.name,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            onValueChange = { onEventValueChange(uiState.addEventDetails.copy(name = it)) },
            label = { Text(text = "Event Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = uiState.addEventDetails.initialBudget,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            onValueChange = {
                onEventValueChange(
                    uiState.addEventDetails.copy(initialBudget = it)
                )
            },
            label = { Text(text = "Initial Budget") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerUi(
    shouldOpenDialog: Boolean,
    state: DatePickerState,
    onDismiss: () -> Unit,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (shouldOpenDialog) {
        val confirmEnable by remember {
            derivedStateOf { state.selectedDateMillis != null }
        }
        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(
                    enabled = confirmEnable,
                    onClick = onConfirm
                ) {
                    Text("Ok")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onCancel
                ) {
                    Text("Cancel")
                }
            }

        ) {
            DatePicker(state = state)
        }
    }
}

@Composable
fun DatePickerButtonUi(
    confirmedDateMillis: Long?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ElevatedButton(
            onClick = onClick
        ) {
            Text("Select Date")
        }
        Text(text = formatDate(ms = confirmedDateMillis) ?: "No Date Selected")
    }

}

@Preview(showBackground = true)
@Composable
private fun EventFormPreview() {
    EventForm(
        uiState = AddEventUiState(),
        onEventValueChange = {},
        onSaveClick = {}
    )
}












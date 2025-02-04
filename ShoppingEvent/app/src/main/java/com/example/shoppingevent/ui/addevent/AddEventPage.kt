package com.example.shoppingevent.ui.addevent

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.shoppingevent.customcomposables.ShoppingAppBar

@Composable
fun AddEventPage(
    navigateBack: () -> Unit,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            ShoppingAppBar(
                title = "Add Event",
                canNavigateBack = true,
                navigateUp = navigateUp
            )
        }
    ) {
        Text(
            text = "Add Event",
            modifier = modifier.padding(it)
        )
    }
}
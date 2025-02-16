package com.example.shoppingevent.customcomposables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissibleItem(
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
    child: @Composable () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }
    var dismissItem by remember { mutableStateOf(false) }
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            when (it) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    return@rememberSwipeToDismissBoxState true
                }

                SwipeToDismissBoxValue.EndToStart -> {
                    showDialog = true
                    return@rememberSwipeToDismissBoxState false
                }

                SwipeToDismissBoxValue.Settled -> {
                    return@rememberSwipeToDismissBoxState false
                }
            }
        },
        positionalThreshold = {
            it * 0.75f
        },
    )

    if (showDialog) {
        AlertDialog(
            title = { Text("Delete Item") },
            text = { Text("Are you sure you want to delete item?") },
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                IconButton(
                    onClick = {
                        showDialog = false
                        dismissItem = true
                        onDelete()
                    }
                ) {
                    Icon(Icons.Default.Done, contentDescription = "Delete Item")
                }
            },
            dismissButton = {
                IconButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Cancel")
                }
            }

        )
    }

    if (dismissItem) {
        LaunchedEffect(Unit) {
            dismissState.dismiss(SwipeToDismissBoxValue.EndToStart)
        }
    }

    SwipeToDismissBox(
        state = dismissState,
        modifier = modifier.animateContentSize(),
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            val backgroundColor by animateColorAsState(
                when (dismissState.targetValue) {
                    SwipeToDismissBoxValue.StartToEnd -> Color.Transparent
                    SwipeToDismissBoxValue.EndToStart -> Color.Red
                    SwipeToDismissBoxValue.Settled -> Color.LightGray
                },
                label = ""
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .background(backgroundColor),
                contentAlignment = Alignment.CenterEnd

            ) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Item")
            }
        }
    ) {
        child()
    }
}



























package com.example.shoppingevent.data.entities

data class ShoppingEvent(
    val id: Long = 0,
    val name: String,
    val initialBudget: Double = 0.0,
    val totalCost: Double = 0.0,
    val eventDate: String,
    val completed: Boolean = false,
)

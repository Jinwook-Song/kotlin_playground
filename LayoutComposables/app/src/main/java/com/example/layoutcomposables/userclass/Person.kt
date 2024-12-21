package com.example.layoutcomposables.userclass

data class Person(
    val id: Int,
    val name: String,
    val email: String,
)

val personListData = List(40) { index ->
    val id = index + 1
    Person(
        id = id,
        name = "Person $id",
        email = "person$id@example.com"
    )
}
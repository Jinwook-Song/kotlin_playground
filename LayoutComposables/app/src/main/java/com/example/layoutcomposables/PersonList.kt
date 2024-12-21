package com.example.layoutcomposables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layoutcomposables.userclass.Person
import com.example.layoutcomposables.userclass.personListData

@Composable
fun PersonList(modifier: Modifier = Modifier) {
    PersonListWithLazyColumn(personList = personListData, modifier = modifier)
}

@Composable
fun PersonListWithLazyColumn(
    personList: List<Person>, modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(personList) { person ->
            PersonItem(person)
        }
    }
}

@Composable
fun PersonListWithColumn(
    personList: List<Person>, modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        for (person in personList)
            PersonItem(person)

    }
}

@Composable
fun PersonItem(person: Person, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = Color.Green)
    ) {
        Text(
            text = person.name,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = person.email,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )
    }

}

@Preview
@Composable
private fun PersonListPreview() {
    MyApp()

}
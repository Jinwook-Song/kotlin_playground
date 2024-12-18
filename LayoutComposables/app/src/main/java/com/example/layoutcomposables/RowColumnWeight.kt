package com.example.layoutcomposables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RowColumnWeight(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Surface(
                color = Color.Red,
                modifier = Modifier.weight(1f).fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("1")
                }
            }
            Surface(
                color = Color.Green,
                modifier = Modifier.weight(1f).fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("2")
                }
            }
        }
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Surface(
                color = Color.Blue,
                modifier = Modifier.weight(1f).fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("3")
                }
            }
            Surface(
                color = Color.Yellow,
                modifier = Modifier.weight(1f).fillMaxHeight()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("4")
                }
            }
        }
    }
}
@Preview
@Composable
private fun RowColumnWeightPreview() {
    MyApp()
}
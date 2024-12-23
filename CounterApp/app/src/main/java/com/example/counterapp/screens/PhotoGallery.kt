package com.example.counterapp.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.counterapp.MyApp
import com.example.counterapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoGallery(modifier: Modifier = Modifier) {
    val photos = listOf(
        R.drawable.yeonjae00,
        R.drawable.yeonjae01,
        R.drawable.yeonjae02,
        R.drawable.yeonjae03,
        R.drawable.yeonjae04,
        R.drawable.yeonjae05,
        R.drawable.yeonjae06,
        R.drawable.yeonjae07,
        R.drawable.yeonjae08,
        R.drawable.yeonjae09,
    )

    val index = remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Photo Gallery")
                },
                actions = {
                    Text(
                        "Showing: ${index.intValue + 1}/${photos.size}",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                modifier = Modifier.weight(5f),
            ) {
                Crossfade(
                    targetState = index.intValue,
                    animationSpec = tween(durationMillis = 500),
                    label = "Photo"
                ) {
                    Image(
                        painter = painterResource(photos[it]),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Surface(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
                    .wrapContentSize(align = Alignment.Center)
            ) {
                Row {
                    OutlinedButton(
                        enabled = index.intValue > 0,
                        onClick = {
                            index.intValue--
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Previous")
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    OutlinedButton(
                        enabled = index.intValue < photos.size - 1,
                        onClick = {
                            index.intValue++
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Next")
                    }
                }
            }
        }

    }
}


@Preview
@Composable
private fun PhotoGalleryPreview() {
    MyApp()
}
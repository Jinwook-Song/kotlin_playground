package com.example.timerapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.timerapp.ui.theme.TimerAppTheme
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimerApp()
        }
    }
}

@Composable
fun TimerApp(modifier: Modifier = Modifier) {
    TimerAppTheme {
        TimerScreen(modifier)
    }
}

@Composable
fun TimerScreen(modifier: Modifier = Modifier) {
    val initialTime = 10L
    var remainingTime: Long by remember {
        mutableLongStateOf(initialTime)
    }
    var isRunning: Boolean by remember {
        mutableStateOf(false)
    }
    var progress: Float by remember {
        mutableFloatStateOf(0f)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                progress = { progress },
                color = Color.Yellow,
                strokeWidth = 10.dp,
                trackColor = Color.Gray,
                modifier = Modifier.size(200.dp),
            )
            Text(
                text = formatTime(remainingTime),
                style = MaterialTheme.typography.displayMedium
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            IconButton(onClick = { isRunning = !isRunning }) {
                val icon = if (isRunning) R.drawable.pause else R.drawable.play
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = if (isRunning) "Pause" else "Play",
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            IconButton(onClick = {
                isRunning = false
                progress = 0f
                remainingTime = initialTime
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.refresh),
                    contentDescription = "Refresh",
                    modifier = Modifier.size(50.dp)
                )
            }
        }

    }
}

@SuppressLint("SimpleDateFormat")
fun formatTime(time: Long): String {
    return SimpleDateFormat("mm:ss").format(Date(time * 1000))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TimerApp()
}
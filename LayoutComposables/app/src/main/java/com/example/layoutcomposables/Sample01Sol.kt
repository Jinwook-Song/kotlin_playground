package com.example.layoutcomposables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Sample01Sol(modifier: Modifier) {
    ProfileCard(modifier = modifier)
    ProfileBody(modifier = modifier)
}

@Composable
fun ProfileCard(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        ProfileBanner()
    }


}

@Composable
fun ProfileBanner(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.sample_01_bg),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

@Composable
fun ProfileBody(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(top = 125.dp)
    ) {
        ProfileAvatar()
        Spacer(modifier = Modifier.height(20.dp))
        HeaderSection()
        Divider()
        ContactInfo()
        Divider()
        BottomSection()

    }
}

@Composable
fun ProfileAvatar(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.avatar),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(150.dp)
            .border(
                border = BorderStroke(5.dp, Color.Green),
                CircleShape
            )
            .clip(CircleShape)
    )

}

@Composable
fun HeaderSection(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Jinwook Song", fontSize = 22.sp)
        Text("Kotilin", fontSize = 16.sp, fontStyle = FontStyle.Italic, color = Color.Gray)

    }
}

@Composable
fun ContactInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(Icons.Rounded.Call, contentDescription = null)
            Spacer(modifier = Modifier.width(20.dp))
            Text("+821012345678")
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(Icons.Rounded.Email, contentDescription = null)
            Spacer(modifier = Modifier.width(20.dp))
            Text("jinwook@gmail.com")
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(Icons.Rounded.LocationOn, contentDescription = null)
            Spacer(modifier = Modifier.width(20.dp))
            Text("서울 성동구 XXX-XX")
        }


    }
}

@Composable
fun BottomSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .wrapContentSize()
    ) {
        Button(
            onClick = {},
            modifier = modifier
                .size(200.dp, 50.dp)
                .padding(2.dp),
            shape = RoundedCornerShape(percent = 16)
        ) {
            Text("All Course", fontSize = 16.sp)
        }
        Button(
            onClick = {},
            modifier = modifier
                .size(200.dp, 50.dp)
                .padding(2.dp),
            shape = RoundedCornerShape(percent = 16)
        ) {
            Text("View BIO", fontSize = 16.sp)
        }
    }
}


@Composable
fun Divider(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(10.dp))
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .border(BorderStroke(width = 2.dp, color = Color.Gray))
    ) { }

}

@Preview(showBackground = true)
@Composable
fun Sample01SolPreview() {
    MyApp()
}
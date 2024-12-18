package com.example.layoutcomposables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Sample01(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // 1. 정사각형 Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(bottom = 20.dp)
        ) {
            // 상단 절반 이미지
            Image(
                painter = painterResource(id = R.drawable.sample_01_bg), // Replace with your image
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            // 가운데 Avatar
            Image(
                painter = painterResource(id = R.drawable.avatar), // Replace with your avatar
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(4.dp, Color.DarkGray, CircleShape)
                    .align(Alignment.Center)
            )
        }

        // 이름 및 직업 텍스트
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Syed Tanvir Ahmad",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Trainer, Mobile Apps Development",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 2. Card Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 32.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                ContactRow(R.drawable.avatar, "+8801522493454")
                ContactRow(R.drawable.avatar, "tanvir.ahmad.bonny@gmail.com")
                ContactRow(R.drawable.avatar, "Mirpur 17, Dhaka, Bangladesh")
            }
        }


        // 3. 하단 버튼들
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    bottom = 40.dp
                )

            
        ) {
            RoundedButton(text = "ALL COURSES", color = Color(0xFF7E57C2))
            Spacer(modifier = Modifier.height(8.dp))
            RoundedButton(text = "READ BIO", color = Color(0xFF7E57C2))
        }
    }
}

@Composable
fun ContactRow(iconId: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Image(
            painter = painterResource(id = iconId), // Replace with actual drawable
            contentDescription = "Icon",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, fontSize = 16.sp)
    }
}

@Composable
fun RoundedButton(text: String, color: Color) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .height(50.dp)
    ) {
        Text(text = text, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}


@Preview(showBackground = true)
@Composable
fun Sample01Preview() {
    MyApp()
}
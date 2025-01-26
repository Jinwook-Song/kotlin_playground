package com.example.weatherapp.utils

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.weatherapp.R


val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val fontName = GoogleFont("Jua")

/**
 * 앱의 폰트 패밀리를 정의
 *
 * 다운로드 가능한 폰트에 대한 자세한 내용은 [다운로드 가능한 폰트](https://developer.android.com/develop/ui/compose/text/fonts?hl=ko#downloadable-fonts)를 참조.
 */
val appFontFamily = FontFamily(
    Font(googleFont = fontName, fontProvider = provider)
)


package com.roshnab.donote.ui.theme

import androidx.compose.material3.Typography
import com.roshnab.donote.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Rubik = FontFamily(
    Font(R.font.rubik_light, weight = FontWeight.Light),
    Font(R.font.rubik_regular, weight = FontWeight.Normal),
    Font(R.font.rubik_medium, weight = FontWeight.Medium),
    Font(R.font.rubik_bold, weight = FontWeight.Bold),
    Font(R.font.rubik_black, weight = FontWeight.Black),

    // Italics
    Font(R.font.rubik_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(R.font.rubik_lightitalic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(R.font.rubik_mediumitalic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(R.font.rubik_bolditalic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.rubik_blackitalic, weight = FontWeight.Black, style = FontStyle.Italic),
    Font(R.font.rubik_extrabold, weight = FontWeight.ExtraBold),
    Font(R.font.rubik_extrabolditalic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(R.font.rubik_semibold, weight = FontWeight.SemiBold),
    Font(R.font.rubik_semibolditalic, weight = FontWeight.SemiBold, style = FontStyle.Italic)
)

val Mozilla = FontFamily(
    Font(R.font.mozilla_regular, FontWeight.Normal),
    Font(R.font.mozilla_light, FontWeight.Light),
    Font(R.font.mozilla_bold, FontWeight.Bold),
    Font(R.font.mozilla_medium, FontWeight.Medium),
    Font(R.font.mozilla_semibold, FontWeight.SemiBold)
)
val Typography = Typography(
//    displayLarge = TextStyle(
//        fontFamily = MyFontFamily,
//        fontWeight = FontWeight.Black,
//        fontSize = 36.sp,
//        lineHeight = 44.sp
//    ),
//    headlineMedium = TextStyle(
//        fontFamily = MyFontFamily,
//        fontWeight = FontWeight.Bold,
//        fontSize = 24.sp,
//        lineHeight = 32.sp
//    ),
//    titleMedium = TextStyle(
//        fontFamily = MyFontFamily,
//        fontWeight = FontWeight.Medium,
//        fontSize = 20.sp,
//        lineHeight = 28.sp
//    ),
//    bodyLarge = TextStyle(
//        fontFamily = MyFontFamily,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    ),
//    bodyMedium = TextStyle(
//        fontFamily = MyFontFamily,
//        fontWeight = FontWeight.Normal,
//        fontSize = 14.sp,
//        lineHeight = 20.sp
//    ),
//    labelSmall = TextStyle(
//        fontFamily = MyFontFamily,
//        fontWeight = FontWeight.Medium,
//        fontSize = 12.sp,
//        lineHeight = 16.sp
//    )
//)

//val BodyLargeItalic = TextStyle(
//    fontFamily = MyFontFamily,
//    fontWeight = FontWeight.Normal,
//    fontStyle = FontStyle.Italic,
//    fontSize = 16.sp,
//    lineHeight = 24.sp
//)
//
//val BodyMediumItalic = TextStyle(
//    fontFamily = MyFontFamily,
//    fontWeight = FontWeight.Light,
//    fontStyle = FontStyle.Italic,
//    fontSize = 14.sp,
//    lineHeight = 20.sp
)
package com.weyamf.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val MyTypography = Typography(
    titleLarge = TextStyle(
      fontSize = 40.sp,
        color = yellow
    ),

    titleMedium = TextStyle(
      fontSize = 26.sp,
        color = cloud
    ),
    displayLarge = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = charcoal
    ),
    displayMedium = TextStyle(
        color = charcoal,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),
    bodyMedium = TextStyle(
        color = green
    ),
    bodySmall = TextStyle(
      color = cloud,
      fontSize =  14.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        color = green,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)


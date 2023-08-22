package com.example.littlelemon

import android.provider.CalendarContract.Colors
import android.widget.HeaderViewListAdapter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.LittleLemonTheme

class OnBoarding {
}

@Composable
fun Onboarding (){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(colorResource(id = R.color.white))
            .padding(top = 10.dp),
    contentAlignment = Alignment.TopCenter
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Header image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview(){
    LittleLemonTheme {
        Onboarding()
    }
}
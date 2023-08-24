package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.MyTypography
import com.example.littlelemon.ui.theme.charcoal
import com.example.littlelemon.ui.theme.cloud
import com.example.littlelemon.ui.theme.yellow

class OnBoarding {
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardScreen(navController: NavHostController) {

    var firstname by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var lastname by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var emailaddress by remember {
        mutableStateOf(TextFieldValue(""))
    }

    val textFieldColorsForInput = TextFieldDefaults.outlinedTextFieldColors(textColor = charcoal, containerColor = cloud)

    Column(modifier = Modifier
        .background(color = cloud)
        .fillMaxHeight()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = cloud),
            contentAlignment = Alignment.TopCenter
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Header image",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(250.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(colorResource(id = R.color.green))
        ){
            Text(
                text = "Let's get to know you!",
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.Center),
                style = MyTypography.titleLarge
            )
        }

        Text(
            text = "Personal Information",
            color = colorResource(id = R.color.black),
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 50.dp),
            style = MyTypography.bodyLarge
        )

        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()) {
            OutlinedTextField(
                value = firstname,
                onValueChange = {firstname = it},
                placeholder = { Text(text = "First Name")},
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = textFieldColorsForInput

            )
            OutlinedTextField(
                value = lastname,
                onValueChange = {lastname = it},
                label = { Text(text = "Last Name")},
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = textFieldColorsForInput)
            OutlinedTextField(
                value = emailaddress,
                onValueChange = {emailaddress = it},
                label = { Text(text = "Email Address")},
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = textFieldColorsForInput)

            Button(
                onClick = {
                navController.navigate(Home.route)
                }
                ,modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = yellow ,
                    contentColor = charcoal
                )
            ){
                Text(text = "Register")
            }
        }


    }


}
/*
@Preview(showBackground = true)
@Composable
fun OnBoardingPreview(){
    LittleLemonTheme {
        OnboardScreen()
    }
}


 */
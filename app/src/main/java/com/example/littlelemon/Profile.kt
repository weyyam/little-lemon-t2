package com.example.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "ProfileScreen")
        Button(
            onClick = {
                navController.navigate(Home.route)
            }) {
            Text(text = "BackButton")
        }
    }
}
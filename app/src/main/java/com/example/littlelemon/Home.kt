package com.example.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "HomeScreen")
        Button(
            onClick = {
            navController.navigate(Profile.route)
        }) {
            Text(text = "Profile")
        }
    }
}
package com.example.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    var savedFirstName = PreferenceHelper.getFirstName(context)
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
        TextField(value = savedFirstName, onValueChange = { savedFirstName = it })
    }
}
package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                ) {
                    MyNavigation()
                }
            }
        }
    }
}

@Composable
fun MyNavigation(){

    val viewModel: OnboardingViewModel = viewModel()

    val navController = rememberNavController()

    val hasRegistered by remember { derivedStateOf { viewModel.hasUserRegistered() } }

    LaunchedEffect(key1 = hasRegistered){
        if (hasRegistered){
            navController.navigate(Home.route){
                popUpTo(Onboard.route){inclusive = true}
            }

        }
    }

    NavHost(navController = navController, startDestination = Onboard.route){
       composable(Onboard.route){
           OnboardScreen(navController)
       }
       composable(Home.route){
           HomeScreen(navController)
       }
       composable(Profile.route){
           ProfileScreen(navController)
       }
    }
}
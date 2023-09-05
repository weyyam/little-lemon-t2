package com.weyamf.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.weyamf.littlelemon.DatabaseHelper.saveToDatabase
import com.weyamf.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val client = HttpClient(Android){
        install(ContentNegotiation){
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val menuItemsLiveData = MutableLiveData<List<String>>()

    private suspend fun fetchMenuData(): MenuNetworkdata {
        return client.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json").body()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        lifecycleScope.launch(Dispatchers.IO) {
            val menuData = fetchMenuData()
            val databaseEntities = mapToDatabaseModel(menuData)
            saveToDatabase(databaseEntities, this@MainActivity)
        }

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
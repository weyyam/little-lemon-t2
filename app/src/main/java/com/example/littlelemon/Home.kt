package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun HomeScreen(navController: NavHostController) {


    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var menuItems by remember { mutableStateOf(listOf<MenuItemEntity>())}

    LaunchedEffect(key1 = Unit) {
        val items = withContext(Dispatchers.IO) {
            DatabaseHelper.getMenuItems(context)
        }
        menuItems = items
    }



    Row(
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

    LazyColumn{
        items(menuItems){ menuItem ->
            MenuItemRow(menuItem = menuItem)

        }
    }
}

@Composable
fun MenuItemRow(menuItem: MenuItemEntity) {
    Text(text = menuItem.title)

}

fun mapToDatabaseModel(networkData: MenuNetworkdata): List<MenuItemEntity>{
    return networkData.menu.map { menuItem ->
        MenuItemEntity(
            id = menuItem.id,
            title = menuItem.title,
            description = menuItem.description,
            price = menuItem.price,
            image = menuItem.image
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController = rememberNavController())
}
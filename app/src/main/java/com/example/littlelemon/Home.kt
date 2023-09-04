package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.MyTypography
import com.example.littlelemon.ui.theme.cloud
import com.example.littlelemon.ui.theme.green
import com.example.littlelemon.ui.theme.yellow
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


    Column(
        modifier = Modifier
            .background(color = cloud)
            .fillMaxHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = cloud)
                .height(100.dp),
        verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Header image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1.25f)
            )
            Box(modifier = Modifier
                .weight(0.5f)
                .padding(2.dp)){
                Button(
                    onClick = {
                        navController.navigate(Profile.route)
                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile photo",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .fillMaxSize())

                }

            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(color = green)
                .padding(10.dp)
        ){
            Column {
                Text(text = "Little Lemon",
                    style = MyTypography.titleLarge
                )
                Text(text = "Chicago",
                    style = MyTypography.titleMedium)

                Text(text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                modifier = Modifier.fillMaxWidth(0.5f).padding(vertical = 15.dp),
                style = MyTypography.bodySmall)

            }

        }

        LazyColumn{
            items(menuItems){ menuItem ->
                MenuItemRow(menuItem = menuItem)

            }
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
package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.graphics.RectangleShape
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
import kotlin.math.round


@OptIn(ExperimentalMaterial3Api::class)
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
                    .weight(1.35f)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(0.45f)
                    .clip(CircleShape)
                    .clickable { navController.navigate(Profile.route) })
        }
        //Green Box Little Lemon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(color = green)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){


            Column (
                modifier = Modifier.weight(0.7f),
                verticalArrangement = Arrangement.Center){
                Text(text = "Little Lemon",
                    style = MyTypography.titleLarge
                )
                Text(text = "Chicago",
                    style = MyTypography.titleMedium)

                Text(text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .padding(vertical = 15.dp),
                style = MyTypography.bodySmall)

            }

            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero image for home page",
                modifier = Modifier
                    .weight(0.4f)
                    .clip(RoundedCornerShape(8))
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.6f),
                contentScale = ContentScale.Crop

            )}

        var query by remember { mutableStateOf("")}
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = green)
        ) {
            TextField(
                value = query,
                onValueChange = {query = it},
                leadingIcon = { Icon(
                    Icons.Default.Search,
                    contentDescription = "Search Icon")
                },
                placeholder = { Text(text = "Search")},
                singleLine = true,
                shape = RoundedCornerShape(15),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .padding(16.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.White
                )
            )

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
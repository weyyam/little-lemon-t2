package com.weyamf.littlelemon

import android.widget.Toast
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.weyamf.littlelemon.ui.theme.MyTypography
import com.weyamf.littlelemon.ui.theme.charcoal
import com.weyamf.littlelemon.ui.theme.cloud
import com.weyamf.littlelemon.ui.theme.yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    var savedFirstName = PreferenceHelper.getFirstName(context)
    var savedLastName = PreferenceHelper.getLastName(context)
    var savedEmailaddress = PreferenceHelper.getEmailAddress(context)
    val viewModel: OnboardingViewModel = viewModel()


    Column(
        modifier = Modifier
            .background(color = cloud)
            .fillMaxHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = cloud),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { navController.navigate(Home.route) },
                colors = ButtonDefaults.buttonColors(yellow),
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .weight(0.5f)
                ) {
                Image(
                    painter = painterResource(id = R.drawable.round_arrow_back_24),
                    contentDescription = "Back button to get to home page")

            }

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Header image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1.25f)
                    .fillMaxSize()
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile photo",
            modifier = Modifier
                .padding(5.dp)
                .weight(0.5f)
                .clip(CircleShape))


        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(colorResource(id = R.color.green))
        ) {
            Text(
                text = "Edit Profile!",
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.Center),
                style = MyTypography.titleLarge
            )
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Personal Information",
                color = colorResource(id = R.color.black),
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 50.dp),
                style = MyTypography.bodyLarge
            )

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = savedFirstName,
                    onValueChange = { savedFirstName = it },
                    placeholder = { Text(text = "First Name") },
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true

                )
                OutlinedTextField(
                    value = savedLastName,
                    onValueChange = { savedLastName = it },
                    label = { Text(text = "Last Name") },
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true
                )
                OutlinedTextField(
                    value = savedEmailaddress,
                    onValueChange = { savedEmailaddress = it },
                    label = { Text(text = "Email Address") },
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true
                )
            }
            Button(onClick = {
                viewModel.signOut()
                navController.navigate(Onboard.route){
                    popUpTo(navController.graph.startDestinationId){inclusive = true}
                }
                Toast.makeText(context, "Signed out successfully!", Toast.LENGTH_SHORT).show()
            }
                , modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults
                    .buttonColors(
                        containerColor = yellow,
                        contentColor = charcoal
                    ))
            {
                Text(text = "Sign Out")
            }

            Button(
                onClick = {/*TODO*/}
                ,modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = yellow ,
                    contentColor = charcoal
                )){
                Text(text = "Save Changes")
            }
        }

    }


}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreen(navController = rememberNavController())
}


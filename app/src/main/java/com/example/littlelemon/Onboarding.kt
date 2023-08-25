package com.example.littlelemon

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.littlelemon.PreferenceHelper.getFirstName
import com.example.littlelemon.ui.theme.MyTypography
import com.example.littlelemon.ui.theme.charcoal
import com.example.littlelemon.ui.theme.cloud
import com.example.littlelemon.ui.theme.yellow

class OnBoarding {
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardScreen(navController: NavHostController, viewModel: OnboardingViewModel = viewModel()) {

    val firstname = remember {
        mutableStateOf("")
    }
    val lastname = remember {
        mutableStateOf("")
    }
    val emailaddress = remember {
        mutableStateOf("")
    }

    val textFieldColorsForInput = TextFieldDefaults.outlinedTextFieldColors(textColor = charcoal, containerColor = cloud)
    val context = LocalContext.current

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
                value = firstname.value,
                onValueChange = {firstname.value = it},
                placeholder = { Text(text = "First Name")},
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = textFieldColorsForInput,
                singleLine = true

            )
            OutlinedTextField(
                value = lastname.value,
                onValueChange = {lastname.value = it},
                label = { Text(text = "Last Name")},
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = textFieldColorsForInput,
                singleLine = true)
            OutlinedTextField(
                value = emailaddress.value,
                onValueChange = {emailaddress.value = it},
                label = { Text(text = "Email Address")},
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = textFieldColorsForInput,
                singleLine = true)

            Button(
                onClick = {

                    if(firstname.value.isBlank() || lastname.value.isBlank() || emailaddress.value.isBlank()){
                        Toast.makeText(
                            context,
                            "Please fill in all registration fields!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        Toast.makeText(
                            context,
                            "Registered!",
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.saveFirstName(firstname.value)
                        viewModel.saveLastName(lastname.value)
                        viewModel.saveEmailAddress(emailaddress.value)
                        navController.navigate(Home.route)
                    }

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

class OnboardingViewModel(application: Application) : AndroidViewModel(application){
    private val context = application.applicationContext
    private val sharedPreferences = context.getSharedPreferences("Onboarding_preferences", Context.MODE_PRIVATE)

    fun saveFirstName(value: String){
        sharedPreferences.edit().putString("firstname", value).apply()
    }
    fun saveLastName(value: String){
        sharedPreferences.edit().putString("lastname", value).apply()
    }

    fun saveEmailAddress(value: String){
        sharedPreferences.edit().putString("emailaddress", value).apply()
    }

    private fun getFirstName(): String{
        return sharedPreferences.getString("firstname", "") ?: ""
    }

    fun hasUserRegistered(): Boolean{
        return getFirstName().isNotBlank()
    }

    fun signOut(){
        sharedPreferences.edit().clear().apply()
    }

}
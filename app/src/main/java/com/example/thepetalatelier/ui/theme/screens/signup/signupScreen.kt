package com.example.thepetalatelier.ui.theme.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.thepetalatelier.R
import com.example.thepetalatelier.data.AuthViewModel
import com.example.thepetalatelier.navigation.ROUTE_LOGIN
import com.example.thepetalatelier.navigation.ROUTE_REGISTER
//import com.example.thepetalatelier.data.AuthViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SignupScreen(navController: NavController){


    val context = LocalContext.current
    val authViewModel: AuthViewModel = viewModel()
    val isloading by authViewModel.isLoading.collectAsState()
    var userName by remember {
        mutableStateOf(value = "")
    }
    var email by remember {
        mutableStateOf(value = "")
    }
    var password by remember {
        mutableStateOf(value = "")
    }
    var confirmPassword by remember {
        mutableStateOf(value = "")
    }







    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "SIGN UP ",
            fontSize = 30.sp,
            color = Color.White,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.Black)
                .padding(20.dp)
                .fillMaxWidth()
        )
        Image(
            painter = painterResource(id = R.drawable.flor),
            contentDescription = "flower logo",
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = userName,
            onValueChange = { newUsername -> userName = newUsername },
            label = { Text(text = "Enter Username") },
            placeholder = { Text(text = "Please enter Username") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { newEmail -> email = newEmail },
            label = { Text(text = "Enter Email") },
            placeholder = { Text(text = "Please enter Email") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { newPassword -> password = newPassword },
            label = { Text(text = "Enter Password") },
            placeholder = { Text(text = "Please enter Password") }
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { conPass -> confirmPassword = conPass },
            label = { Text(text = "Confirm Password") },
            placeholder = { Text(text = "Please Confirm Password") }
        )
        Button(
            onClick = {
               authViewModel.signup(userName, email, password, confirmPassword, navController, context)
            },
           enabled =  !isloading,
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterHorizontally)

        ) {
           if (isloading) {
                CircularProgressIndicator(color = Color.Black, strokeWidth = 4.dp)
            } else {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "SIGN UP HERE"
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            ClickableText(
                text = AnnotatedString("Already have an account? Register here"),
                onClick = {
                    navController.navigate(ROUTE_REGISTER)

                },
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            )

        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignupPreview(){
    SignupScreen(rememberNavController())
}




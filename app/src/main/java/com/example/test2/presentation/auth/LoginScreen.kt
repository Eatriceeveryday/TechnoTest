package com.example.test2.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.test2.R
import com.example.test2.common.UiEvents
import com.example.test2.presentation.global.LoadingState
import com.example.test2.ui.navigation.Screen
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navHost: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
){
    val email = viewModel.email.value
    val password = viewModel.password.value
    val isloading = remember {
        mutableStateOf(false)
    }
    val isFailed = remember {
        mutableStateOf(false)
    }
    val errMsg = remember {
        mutableStateOf("")
    }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true){
        viewModel.eventFlow.collectLatest { event ->
            when(event){
                is UiEvents.LoadingEvent -> {
                    isloading.value = event.isLoading
                }
                is UiEvents.NavigateEvent -> {
                    navHost.navigate(event.route){
                        popUpTo(Screen.Login.route){
                            inclusive = true
                        }
                    }
                }
                is UiEvents.ErrorEvent -> {
                    isFailed.value = true
                    errMsg.value = event.message
                }
            }
        }
    }

    Box{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_technopartner),
                contentDescription ="logo Technopartner",
                contentScale = ContentScale.Fit,
                modifier = Modifier.padding(bottom = 50.dp, top = 50.dp)
            )

            Text(
                text = "Email", style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            ),
                modifier = Modifier
                    .padding(vertical = 10.dp),
                textAlign = TextAlign.Center)
            
            TextField(
                value = email.text,
                onValueChange = {
                    viewModel.setEmail(it)},
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .background(Color.Transparent),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),

            )

            Text(
                text = "Password", style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                ),
                modifier = Modifier
                    .padding(vertical = 10.dp),
                textAlign = TextAlign.Center)

            TextField(
                value = password.text,
                onValueChange = {
                    viewModel.setPassword(it)},
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                )

            if (isFailed.value){
                Text(
                    text = errMsg.value,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.Red
                    ),
                    modifier = Modifier
                        .padding(vertical = 10.dp),
                    textAlign = TextAlign.Center)
            }

            Button(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(height = 40.dp)
                    .width(width = 100.dp)
                    .shadow(
                        elevation = 4.dp,
                        shape = RoundedCornerShape(10),
                    ),
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(
                    Color.White
                ),
                onClick = { viewModel.login() }) {
                Text(
                    text = "Login" ,
                    style = TextStyle(
                        color = Color.Black
                    )
                )
            }
        }

        if (isloading.value){
            LoadingState()
        }
    }
}

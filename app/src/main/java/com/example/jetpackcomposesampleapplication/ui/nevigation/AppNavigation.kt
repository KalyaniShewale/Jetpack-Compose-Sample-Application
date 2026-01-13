package com.example.jetpackcomposesampleapplication.ui.nevigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposesampleapplication.ui.screen.LoginScreen
import com.example.jetpackcomposesampleapplication.ui.screen.RegistrationScreen


@Composable
fun AppNavigation(paddingValues: PaddingValues){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination  = "Login"){
        composable("Login"){
            LoginScreen(navController , paddingValues)
        }
        composable("Home"){
          //  HomeScreen(navController)
        }
        composable("Registration"){
            RegistrationScreen(navController, paddingValues)
        }
    }
}
package com.dodo.flashcards.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dodo.flashcards.architecture.Router
import com.dodo.flashcards.presentation.login_screen.LoginScreen
import com.dodo.flashcards.presentation.register_screen.RegisterScreen
import com.dodo.flashcards.presentation.welcome_screen.WelcomeScreen
import com.dodo.flashcards.util.Screen.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavHost(
    navController : NavHostController,
    startRoute : String,
    router : Router<MainDestination>
){
    AnimatedNavHost(
        navController = navController,
        startDestination = startRoute
    ){
        composable(route = Login.route){
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                // This is temporary to test registration
                RegisterScreen(viewModel = hiltViewModel())
            }
        }
        composable(route = Register.route){
            RegisterScreen(viewModel = hiltViewModel())
        }
        composable(route = Welcome.route){
            WelcomeScreen(viewModel = hiltViewModel())
        }
    }
}

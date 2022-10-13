package com.dodo.flashcards.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dodo.flashcards.architecture.Router
import com.dodo.flashcards.presentation.forgotPass.ForgotPassScreen
import com.dodo.flashcards.presentation.forgotPass.ForgotPassViewModel
import com.dodo.flashcards.presentation.loginScreen.LoginScreen
import com.dodo.flashcards.presentation.loginScreen.LoginScreenViewModel
import com.dodo.flashcards.presentation.registerScreen.RegisterScreen
import com.dodo.flashcards.presentation.registerScreen.RegisterScreenViewModel
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreen
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreenViewModel
import com.dodo.flashcards.util.Screen.*
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavHost(
    navController: NavHostController,
    startRoute: String,
    router: Router<MainDestination>
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startRoute
    ) {
        composable(route = ForgotPass.route) {
            ForgotPassScreen(viewModel = hiltViewModel<ForgotPassViewModel>().apply {
                attachRouter(router)
            })
        }
        composable(route = Login.route) {
            LoginScreen(viewModel = hiltViewModel<LoginScreenViewModel>().apply {
                attachRouter(router)
            })
        }
        composable(route = Register.route) {
            RegisterScreen(viewModel = hiltViewModel<RegisterScreenViewModel>().apply {
                attachRouter(router)
            })
        }
        composable(route = Welcome.route) {
            WelcomeScreen(viewModel = hiltViewModel<WelcomeScreenViewModel>().apply {
                attachRouter(router)
            })
        }
    }
}

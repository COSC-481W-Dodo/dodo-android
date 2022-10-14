package com.dodo.flashcards.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dodo.flashcards.architecture.Router
import com.dodo.flashcards.presentation.MainDestination.*
import com.dodo.flashcards.presentation.MainViewState.*
import com.dodo.flashcards.presentation.theme.FlashcardsAppTheme
import com.dodo.flashcards.util.Screen.*
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(), Router<MainDestination> {

    private lateinit var navController: NavHostController

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            viewModel.viewState.value?.apply {
                val startScreen = if (this is Authenticated) Welcome else Login
                FlashcardsAppTheme {
                    navController = rememberAnimatedNavController()
                    MainNavHost(
                        navController = navController,
                        startRoute = startScreen.route,
                        router = this@MainActivity
                    )
                }
            }
        }
    }

    override fun routeTo(destination: MainDestination) {
        when (destination) {
            is NavigateEditUsername -> navigateEditUsername()
            is NavigateEditPass -> navigateEditPass()
            is NavigateEditProfile -> navigateEditProfile()
            is NavigateForgotPass -> navigateForgotPass()
            is NavigateLogin -> navigateLogin()
            is NavigateRegister -> navigateRegister()
            is NavigateUp -> navigateUp()
            is NavigateWelcome -> navigateWelcome()
        }
    }

    private fun navigateEditUsername() {
        navController.navigate(route = EditUsername.route)
    }

    private fun navigateEditPass() {
        navController.navigate(route = EditPass.route)
    }

    private fun navigateEditProfile() {
        navController.navigate(route = EditProfile.route)
    }

    private fun navigateForgotPass() {
        navController.navigate(route = ForgotPass.route)
    }

    private fun navigateLogin() {
        navController.navigate(route = Login.route) {
            if (navController.currentDestination?.route == Welcome.route) {
                popUpTo(route = Welcome.route) {
                    inclusive = true
                }
            }
        }
    }

    private fun navigateRegister() {
        navController.navigate(route = Register.route)
    }

    private fun navigateUp() {
        navController.navigateUp()
    }

    // Todo, evaluate cleaner way to do this; if this isn't done you can
    // have a bug where you login --> register --> welcome --(logout)--> login, and
    // still have the original login in the backstack.
    private fun navigateWelcome() {
        navController.navigate(route = Welcome.route) {
            popUpTo(route = Register.route) {
                inclusive = true
            }
            popUpTo(route = Login.route) {
                inclusive = true
            }
        }
    }
}
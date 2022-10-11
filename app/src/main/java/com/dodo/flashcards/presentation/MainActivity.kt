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
            is NavigateLogin -> navigateLogin()
            is NavigateUp -> navigateUp()
        }
    }

    private fun navigateLogin() {
        navController.navigate(route = Login.route) {
            popUpTo(route = Welcome.route) {
                inclusive = true
            }
        }
    }

    private fun navigateUp() {
        navController.navigateUp()
    }
}
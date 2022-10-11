package com.dodo.flashcards.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dodo.flashcards.architecture.Router
import com.dodo.flashcards.presentation.register_screen.RegisterScreen
import com.dodo.flashcards.presentation.theme.FlashcardsAppTheme
import com.dodo.flashcards.util.Screen.*
import dagger.hilt.android.AndroidEntryPoint
import com.dodo.flashcards.presentation.MainDestination.*
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@AndroidEntryPoint
class MainActivity : ComponentActivity(), Router<MainDestination> {

    lateinit var navController: NavHostController
    private lateinit var auth: FirebaseAuth

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth

        super.onCreate(savedInstanceState)

        setContent {
            FlashcardsAppTheme {
                navController = rememberAnimatedNavController()
                val currentUser = auth.currentUser
                val startScreen = if (currentUser != null) Welcome.route else Register.route
                MainNavHost(
                    navController = navController,
                    startRoute = startScreen,
                    router = this@MainActivity)
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
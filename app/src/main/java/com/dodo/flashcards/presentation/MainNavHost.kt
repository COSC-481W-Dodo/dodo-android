package com.dodo.flashcards.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.dodo.flashcards.architecture.Router
import com.dodo.flashcards.presentation.editProfileScreen.EditProfileScreen
import com.dodo.flashcards.presentation.editProfileScreen.EditProfileViewModel
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editEmail.EditEmailScreen
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editEmail.EditEmailViewModel
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass.EditPassScreen
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editPass.EditPassViewModel
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editUsername.EditUsernameScreen
import com.dodo.flashcards.presentation.editProfileScreen.subscreens.editUsername.EditUsernameViewModel
import com.dodo.flashcards.presentation.forgotPassScreen.ForgotPassScreen
import com.dodo.flashcards.presentation.forgotPassScreen.ForgotPassViewModel
import com.dodo.flashcards.presentation.loginScreen.LoginScreen
import com.dodo.flashcards.presentation.loginScreen.LoginViewModel
import com.dodo.flashcards.presentation.registerScreen.RegisterScreen
import com.dodo.flashcards.presentation.registerScreen.RegisterViewModel
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewDelegate
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewModel
import com.dodo.flashcards.presentation.viewTagsScreen.ViewTagsScreen
import com.dodo.flashcards.presentation.viewTagsScreen.ViewTagsViewModel
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeScreen
import com.dodo.flashcards.presentation.welcomeScreen.WelcomeViewModel
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
        composable(route = EditEmail.route) {
            EditEmailScreen(viewModel = hiltViewModel<EditEmailViewModel>().apply {
                attachRouter(router)
            })
        }
        composable(route = EditUsername.route) {
            EditUsernameScreen(viewModel = hiltViewModel<EditUsernameViewModel>().apply {
                attachRouter(router)
            })
        }
        composable(route = EditPass.route) {
            EditPassScreen(viewModel = hiltViewModel<EditPassViewModel>().apply {
                attachRouter(router)
            })
        }
        composable(route = EditProfile.route) {
            EditProfileScreen(viewModel = hiltViewModel<EditProfileViewModel>().apply {
                attachRouter(router)
            })
        }
        composable(route = ForgotPass.route) {
            ForgotPassScreen(viewModel = hiltViewModel<ForgotPassViewModel>().apply {
                attachRouter(router)
            })
        }
        composable(route = Login.route) {
            LoginScreen(viewModel = hiltViewModel<LoginViewModel>().apply {
                attachRouter(router)
            })
        }
        composable(route = Register.route) {
            RegisterScreen(viewModel = hiltViewModel<RegisterViewModel>().apply {
                attachRouter(router)
            })
        }
        composable(route = Welcome.route) {
            WelcomeScreen(viewModel = hiltViewModel<WelcomeViewModel>().apply {
                attachRouter(router)
            })
        }
        composable(
            route = ViewCards.route + "/{tags}",
            arguments = listOf(
                navArgument("tags") {
                    type = NavType.StringType
                }
            )
        ) {
            ViewCardsViewDelegate(viewModel = hiltViewModel<ViewCardsViewModel>().apply {
                attachRouter(router)
            })
        }
        composable(route = ViewTags.route) {
            ViewTagsScreen(viewModel = hiltViewModel<ViewTagsViewModel>().apply {
                attachRouter(router)
            })
        }
    }
}
package com.dodo.flashcards.util

sealed class Screen(val route: String) {

    object EditEmail : Screen("EditEmail")
    object EditPass : Screen("EditPass")
    object EditProfile : Screen("EditProfile")
    object EditUsername : Screen("EditUsername")
    object ForgotPass : Screen("ForgotPass")
    object Login : Screen("Login")
    object Register : Screen("Register")
    object ViewCards : Screen("ViewCards")
    object ViewTags : Screen("ViewTags")
    object Welcome : Screen("Welcome")

    fun withArgs(args: Array<Pair<String, String>>? = null): String {
        return buildString {
            append(route)
            args?.forEachIndexed { index, pair ->
                append(if (index == 0) '?' else '&')
                    .append(pair.first)
                    .append('=')
                    .append(pair.second)
            }
        }
    }

    override fun toString(): String = route
}
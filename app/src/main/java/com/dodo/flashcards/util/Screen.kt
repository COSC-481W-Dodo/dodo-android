package com.dodo.flashcards.util

sealed class Screen(val route: String) {
    companion object{

    }

    object Login : Screen("Login")
    object Register : Screen("Register")
    object Welcome : Screen("Welcome")

    object Categories : Screen("Category")
    object Decks : Screen("Decks")
    object CreateCategory : Screen("AddCategory")

    fun withArgs(args: Array<Pair<String, String>>? = null) : String{
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

    override fun toString() : String = route
}
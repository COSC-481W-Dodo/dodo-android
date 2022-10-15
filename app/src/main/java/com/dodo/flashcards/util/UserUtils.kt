package com.dodo.flashcards.util

class UserUtils {
    
    companion object {
        private const val MINIMUM_PASSWORD_LENGTH = 6
    }

    fun isValidUsername(username: String, oldUsername: String? = null): Boolean {
        // Todo, add all valid username change logic here
        return username.isNotEmpty() && username != oldUsername
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= MINIMUM_PASSWORD_LENGTH
    }
}
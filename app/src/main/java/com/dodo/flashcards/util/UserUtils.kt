package com.dodo.flashcards.util

import android.provider.ContactsContract
import android.util.Patterns
import java.lang.Character.isWhitespace

class UserUtils {

    companion object {
        private const val MINIMUM_PASSWORD_LENGTH = 8
    }

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidUsername(username: String, oldUsername: String? = null): Boolean {
        username.forEach {
            if (!it.isLetterOrDigit()) {
                return false
            }
        }
        return username.isNotEmpty() && username != oldUsername
    }

    fun isValidPassword(password: String): Boolean {
        var hasCapital = false
        var hasSpecial = false

        password.forEach {
            if (it.isWhitespace()) {
                return false
            }
            if (it.isUpperCase()) {
                hasCapital = true
            }
            if (!it.isLetterOrDigit()) {
                hasSpecial = true
            }
        }
        return password.length >= MINIMUM_PASSWORD_LENGTH && hasCapital && hasSpecial
    }
}
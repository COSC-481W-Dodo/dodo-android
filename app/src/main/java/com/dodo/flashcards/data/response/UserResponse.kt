package com.dodo.flashcards.data.response

import com.dodo.flashcards.domain.models.User

data class UserResponse(
    override val email: String,
    override val username: String
) : User

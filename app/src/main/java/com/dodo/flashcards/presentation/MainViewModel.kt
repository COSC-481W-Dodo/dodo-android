package com.dodo.flashcards.presentation

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseViewModel
import com.dodo.flashcards.domain.usecases.authentication.GetUserUseCase
import com.google.firebase.auth.FirebaseAuth
import com.dodo.flashcards.presentation.MainViewState.*
import com.dodo.flashcards.util.doOnError
import com.dodo.flashcards.util.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(getUserUseCase: GetUserUseCase) :
    BaseViewModel<MainViewState, MainViewEvent>() {

    init {
        viewModelScope.launch {
            getUserUseCase()
                .doOnSuccess {
                    Authenticated.push()
                }
                .doOnError {
                    Unauthenticated.push()
                }
        }
    }

    override fun onEvent(event: MainViewEvent) {
    }
}
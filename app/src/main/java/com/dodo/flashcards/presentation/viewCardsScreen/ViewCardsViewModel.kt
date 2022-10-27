package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.flashcards.GetFlashcardsUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.Flip
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.SwipedAnyDirection
import com.dodo.flashcards.util.doOnError
import com.dodo.flashcards.util.doOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewCardsViewModel @Inject constructor(
    getFlashcardsUseCase: GetFlashcardsUseCase
) : BaseRoutingViewModel<ViewCardsViewState, ViewCardsViewEvent, MainDestination>() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getFlashcardsUseCase(listOf())
                .doOnSuccess {
                    ViewCardsViewState(
                        currentIndex = 0,
                        currentCard = data[0],
                        nextCard = data[1]
                    ).push()
                }
                .doOnError {
                    // Todo
                }
        }
    }

    override fun onEvent(event: ViewCardsViewEvent) {
        when (event) {
            is SwipedAnyDirection -> onSwiped()
            is Flip -> onFlip()
        }
    }

    private fun onSwiped() {
        TODO("Not yet implemented")
    }

    private fun onFlip() {
        TODO("Not yet implemented")
    }


}
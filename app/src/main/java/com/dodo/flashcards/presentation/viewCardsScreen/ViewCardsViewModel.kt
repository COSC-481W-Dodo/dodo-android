package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.models.Flashcard
import com.dodo.flashcards.domain.usecases.flashcards.GetFlashcardsUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewState.*
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.*
import com.dodo.flashcards.util.doOnError
import com.dodo.flashcards.util.doOnSuccess
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewCardsViewModel @Inject constructor(
    getFlashcardsUseCase: GetFlashcardsUseCase,
    savedStateHandle: SavedStateHandle
) : BaseRoutingViewModel<ViewCardsViewState, ViewCardsViewEvent, MainDestination>() {

    companion object {
        private const val START_INDEX = 0
    }

    private lateinit var cards: List<Flashcard>
    private var currentCardIndex = START_INDEX
    private val tags: List<String> = Gson().fromJson(
        (savedStateHandle.get<String>("tags")) ?: error("Missing tags."),
        object : TypeToken<List<String>>() {}.type
    )

    init {
        CardsLoading.push()
        viewModelScope.launch(Dispatchers.IO) {
            getFlashcardsUseCase(tags)
                .doOnSuccess {
                    if (data.isEmpty()) {
                        CardsLoadError.push()
                        return@doOnSuccess
                    }
                    cards = data
                    resetCards()
                }
                .doOnError {
                    CardsLoadError.push()
                }
        }
    }


    override fun onEvent(event: ViewCardsViewEvent) {
        when (event) {
            is ClickedCard -> onClickedCard()
            is ClickedReturnPreviousCard -> onClickedReturnPreviousCard()
            is SwipedCard -> onSwipedCard()
        }
    }

    private fun onClickedCard() {
        (lastPushedState as? CardsLoaded)?.run {
            copy(currentCardIsFlipped = !currentCardIsFlipped)
        }?.push()
    }

    private fun onClickedReturnPreviousCard() {
        (lastPushedState as? CardsLoaded)?.run {
            val newIndex = --currentCardIndex
            val newCard = cards[newIndex]
            copy(
                currentCardBack = newCard.back,
                currentCardFront = newCard.front,
                currentCardIsFlipped = false,
                hasPreviousCard = newIndex != START_INDEX,
                nextCardFront = currentCardFront
            ).push()
        }
    }

    private fun onSwipedCard() {
        (lastPushedState as? CardsLoaded)?.run {
            val newIndex = ++currentCardIndex
            cards.getOrNull(newIndex)?.let { newCard ->
                copy(
                    currentCardBack = newCard.back,
                    currentCardFront = newCard.front,
                    currentCardIsFlipped = false,
                    hasPreviousCard = true,
                    nextCardFront = cards.getOrNull(currentCardIndex + 1)?.front
                ).push()
            } ?: run {
                // Todo, there are no more cards in the set
                // Placeholder behavior is to jump to start again.
                resetCards()
            }
        }
    }

    private fun resetCards() {
        currentCardIndex = START_INDEX
        CardsLoaded(
            currentCardFront = cards[currentCardIndex].front,
            currentCardBack = cards[currentCardIndex].back,
            currentCardIsFlipped = false,
            hasPreviousCard = false,
            nextCardFront = cards.getOrNull(currentCardIndex + 1)?.front
        ).push()
    }


}
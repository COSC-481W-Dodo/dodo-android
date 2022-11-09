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
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ViewCardsViewModel @Inject constructor(
    getFlashcardsUseCase: GetFlashcardsUseCase,
    savedStateHandle: SavedStateHandle
) : BaseRoutingViewModel<ViewCardsViewState, ViewCardsViewEvent, MainDestination>() {

    companion object {
        private const val START_INDEX = 0
    }

    private lateinit var wholeDeck: List<Flashcard>
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
                    wholeDeck = data
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
            is SwipedCardReset -> onSwipedCardReset()
            is BounceReset -> onBounce()
        }
    }

    private fun onClickedCard() {
        (lastPushedState as? CardsLoaded)?.run {
            copy(
                currentCardIsFlipped = !currentCardIsFlipped,
                currentCardIsScaled = !currentCardIsScaled
            )
        }?.push()
    }

    private fun onBounce() {
        (lastPushedState as? CardsLoaded)?.run {
            copy(
                currentCardIsScaled = !currentCardIsScaled
            )
        }?.push()
    }

    private fun onClickedReturnPreviousCard() {
        (lastPushedState as? CardsLoaded)?.run {
//            val newIndex = --currentCardIndex
            currentCardIndex -= 1
            val newCard = wholeDeck[currentCardIndex]
            copy(
                currentCard = newCard,
                nextCard = wholeDeck.getOrNull(currentCardIndex + 1),
                currentCardIsFlipped = false,
                hasPreviousCard = currentCardIndex != START_INDEX,
            ).push()
        }
    }

    private fun onSwipedCard() {
        (lastPushedState as? CardsLoaded)?.run {
            copy(currentCard = nextCard).push()
        } ?: run {
            // Todo, there are no more wholeDeck in the set
            // Placeholder behavior is to jump to start again.
            //    resetCards()
        }
    }

    private fun onSwipedCardReset() {
        (lastPushedState as? CardsLoaded)?.run {
            currentCardIndex += 1
            wholeDeck.getOrNull(currentCardIndex)?.let { newCard ->
                copy(
                    currentCard = newCard,
                    nextCard = wholeDeck.getOrNull(currentCardIndex + 1),
                    currentCardIsFlipped = false,
                    hasPreviousCard = true,
                ).push()
            }
        }
    }

    private fun resetCards() {
        currentCardIndex = START_INDEX
        CardsLoaded(
            currentCard = wholeDeck[currentCardIndex],
            nextCard = wholeDeck.getOrNull(currentCardIndex + 1),
            currentCardIsFlipped = false,
            currentCardIsScaled = false,
            hasPreviousCard = false,
        ).push()
    }


}
package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.models.Flashcard
import com.dodo.flashcards.domain.usecases.flashcards.GetFlashcardsUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.MainDestination.NavigateUp
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.*
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewState.*
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
                    if (data.isEmpty() || data.size < 2) {
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
            is ClickedNavigateUp -> onClickedNavigateUp()
            is ClickedReturnPreviousCard -> onClickedReturnPreviousCard()
            is ClickedPreviousReset -> onClickedPreviousReset()
            is SwipedCard -> onSwipedCard()
            is SwipedCardReset -> onSwipedCardReset()
        }
    }

    private fun onClickedCard() {
        (lastPushedState as? CardsLoaded)?.run {
            copy(
                isFlipped = !isFlipped,
            )
        }?.push()
    }

    private fun onClickedNavigateUp() {
        routeTo(NavigateUp)
    }

    private fun onClickedReturnPreviousCard() {
        (lastPushedState as? CardsLoaded)?.run {
            wholeDeck.run {
                currentCardIndex = if (currentCardIndex == 0) (size - 1)
                else getPreviousIndex(currentCardIndex)
                copy(dummyCard = get(currentCardIndex)).push()
            }
        }
    }

    private fun onClickedPreviousReset() {
        (lastPushedState as? CardsLoaded)?.run {
            wholeDeck.run {

                copy(
                    dummyCard = null,
                    isFlipped = false,
                    currentCard = get(currentCardIndex),
                    nextCard = get(getNextIndex(currentCardIndex))
                ).push()
            }
        }
    }

    private fun onSwipedCard() {
        (lastPushedState as? CardsLoaded)?.run {
            copy(
                currentCard = nextCard,
                isFlipped = false
            ).push()
        }
    }

    private fun onSwipedCardReset() {
        (lastPushedState as? CardsLoaded)?.run {
            wholeDeck.run {
                currentCardIndex = getNextIndex(currentCardIndex)
                copy(
                    currentCard = get(currentCardIndex),
                    nextCard = get(getNextIndex(currentCardIndex))
                ).push()
            }
        }
    }


    private fun resetCards() {
        currentCardIndex = START_INDEX
        wholeDeck.run {
            CardsLoaded(
                currentCard = get(currentCardIndex),
                nextCard = get(getNextIndex(currentCardIndex)),
                dummyCard = null
            )
        }.push()
    }

    private fun List<Flashcard>.getPreviousIndex(index: Int) =
        if (index == 0) lastIndex else index - 1

    private fun List<Flashcard>.getNextIndex(index: Int) =
        if (index == lastIndex) 0 else index + 1
}
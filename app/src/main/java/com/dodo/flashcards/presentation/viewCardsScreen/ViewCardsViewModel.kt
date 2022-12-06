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

    private lateinit var deckOriginal: List<Flashcard>
    private lateinit var deckUsed: List<Flashcard>
    private var currentCardIndex = START_INDEX
    private val tags: List<String> = Gson().fromJson(
        (savedStateHandle.get<String>("tags")) ?: error("Missing tags."),
        object : TypeToken<List<String>>() {}.type
    )

    private val ownerOnly = (savedStateHandle.get<Boolean>("ownerOnly") ?: "Missing owner only") as Boolean

    init {
        CardsLoading.push()
        viewModelScope.launch(Dispatchers.IO) {
            getFlashcardsUseCase(tags, ownerOnly)
                .doOnSuccess {
                    if (data.isEmpty() || data.size < 2) {
                        CardsLoadError.push()
                        return@doOnSuccess
                    }
                    deckOriginal = data
                    deckUsed = data
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
            is ClickedShuffleDeck -> onClickedShuffle()
            is ClickedNavigateUp -> onClickedNavigateUp()
            is ClickedReturnPreviousCard -> onClickedReturnPreviousCard()
            is SwipedCard -> onSwipedCard()
            is SwipedCardReset -> onSwipedCardReset()
        }
    }

    private fun onClickedShuffle() {
        (lastPushedState as? CardsLoaded)?.run {
            if (isShuffled) {
                deckOriginal
            } else {
                deckOriginal.shuffled()
            }.let {
                copy(
                    currentCard = it[currentCardIndex],
                    nextCard = it[it.getNextIndex(currentCardIndex)],
                    isShuffled = !isShuffled,
                    isFlipped = false
                )
            }
        }?.push()
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
            deckUsed.run {
                currentCardIndex = getPreviousIndex(currentCardIndex)
                copy(
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
            deckUsed.run {
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
        deckUsed.run {
            CardsLoaded(
                currentCard = get(currentCardIndex),
                nextCard = get(getNextIndex(currentCardIndex))
            )
        }.push()
    }

    private fun List<Flashcard>.getPreviousIndex(index: Int) =
        if (index == 0) lastIndex else index - 1

    private fun List<Flashcard>.getNextIndex(index: Int) =
        if (index == lastIndex) 0 else index + 1
}
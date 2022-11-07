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
                    CardsLoaded(
                        currentCardIndex = START_INDEX,
                        currentCardFront = cards[START_INDEX].front,
                        currentCardBack = cards[START_INDEX].back,
                        currentCardIsFlipped = false,
                        nextCardFront = cards[START_INDEX + 1].front
                    ).push()
                }
                .doOnError {
                    CardsLoadError.push()
                }
        }
    }


    override fun onEvent(event: ViewCardsViewEvent) {
        when (event) {
            is ClickedCard -> onClickedCard()
            is SwipedAnyDirection -> onSwiped()
        }
    }

    private fun onClickedCard() {
        (lastPushedState as? CardsLoaded)?.run {
            copy(currentCardIsFlipped = !currentCardIsFlipped)
        }?.push()
    }

    private fun onSwiped() {
        TODO("Not yet implemented")
    }


}
package com.dodo.flashcards.presentation.viewCardsScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dodo.flashcards.architecture.BaseRoutingViewModel
import com.dodo.flashcards.domain.usecases.flashcards.GetFlashcardsUseCase
import com.dodo.flashcards.presentation.MainDestination
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.SwipedAnyDirection
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.CardClicked
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


    init {
        val tagsJson: String = savedStateHandle["tags"] ?: error("Missing tags.")
        val itemType = object : TypeToken<List<String>>() {}.type
        val tags: List<String> = Gson().fromJson(tagsJson, itemType)

        // Todo, debug text
        println("Here $tags")

        println(tags.joinToString { "$it " })
        viewModelScope.launch(Dispatchers.IO) {
            getFlashcardsUseCase(listOf())
                .doOnSuccess {
                    ViewCardsViewState(
                        currentIndex = 0,
                        currentCard = data[0],
                        nextCard = data[1],
                        isFlipped = false
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
            is CardClicked -> onCardClicked()
        }
    }

    private fun onSwiped() {
        TODO("Not yet implemented")
    }

    private fun onFlip() {
        TODO("Not yet implemented")
    }

    private fun onCardClicked() {
        lastPushedState?.run {
            copy(isFlipped = !isFlipped)
        }?.push()
    }
}
package com.dodo.flashcards.presentation.viewCardsScreen.subscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dodo.flashcards.architecture.EventReceiver
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.*
import com.dodo.flashcards.presentation.viewCardsScreen.FlippableFlashCard
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent

@Composable
fun CardsLoaded(
    currentCardBack: String,
    currentCardFront: String,
    currentCardIsFlipped: Boolean,
    hasPreviousCard: Boolean,
    nextCardFront: String?,
    eventReceiver: EventReceiver<ViewCardsViewEvent>
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        if (hasPreviousCard) {
            Button(onClick = { eventReceiver.onEvent(ClickedReturnPreviousCard) }) {
                Text("Previous")
            }
        }
        Button(onClick = { eventReceiver.onEvent(SwipedCard) }) {
            Text("Next")
        }
    }
    FlippableFlashCard(
        isCardFlipped = currentCardIsFlipped,
        onCardClicked = { eventReceiver.onEvent(ClickedCard) },
        frontContent = currentCardFront,
        backContent = currentCardBack,
        modifier = Modifier.fillMaxSize()
    )

}
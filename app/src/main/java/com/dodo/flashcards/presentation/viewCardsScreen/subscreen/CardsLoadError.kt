package com.dodo.flashcards.presentation.viewCardsScreen.subscreen

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import com.dodo.flashcards.architecture.EventReceiver
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.ClickedNavigateUp

@Composable
fun CardsLoadError(eventReceiver: EventReceiver<ViewCardsViewEvent>) {
    Text("There are not enough cards associated with these tags to quiz on.")
    TextButton(onClick = { eventReceiver.onEventDebounced(ClickedNavigateUp) }) {
        Text("Return")
    }
}
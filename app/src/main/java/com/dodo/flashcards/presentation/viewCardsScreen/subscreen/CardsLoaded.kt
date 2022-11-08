package com.dodo.flashcards.presentation.viewCardsScreen.subscreen

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
import com.dodo.flashcards.architecture.EventReceiver
import com.dodo.flashcards.domain.models.Flashcard
import com.dodo.flashcards.presentation.common.commonModifiers.bounceBetweenFloat
import com.dodo.flashcards.presentation.common.commonModifiers.rememberSwipeState
import com.dodo.flashcards.presentation.common.commonModifiers.swipe
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.*
import com.dodo.flashcards.presentation.viewCardsScreen.FlippableFlashCard
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent

@OptIn(ExperimentalMaterialApi::class, ExperimentalSwipeableCardApi::class)
@Composable
fun CardsLoaded(
    cards: List<Flashcard>,
    currentCardBack: String,
    currentCardFront: String,
    currentCardIsFlipped: Boolean,
    currentCardIsScaled: Boolean,
    hasPreviousCard: Boolean,
    nextCardFront: String?,
    eventReceiver: EventReceiver<ViewCardsViewEvent>,
) {
    val ANIMATION_DURATION_MILLIS = 350;

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            if (hasPreviousCard) {
                Button(onClick = { eventReceiver.onEvent(ClickedReturnPreviousCard) }) {
                    Text("Previous")
                }
            }
            Button(onClick = { eventReceiver.onEvent(SwipedCard) }) {
                Text("Next")
            }
        }

        val states = cards.map { it to rememberSwipeableCardState() }

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .align(Alignment.Center)
        ) {
            val swipeState = rememberSwipeState(
                maxWidth = constraints.maxWidth.toFloat(),
                maxHeight = constraints.maxHeight.toFloat()
            )
            Log.d(TAG, "${cards.size}")
            cards.forEachIndexed { index, flashcard ->
/*
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                ) {
*/
                    FlippableFlashCard(
                        modifier = Modifier
                            .fillMaxSize(.88f)
                            .align(Alignment.Center)
                            .swipe(
                                state = swipeState,
                                onDragAccepted = {
                                    eventReceiver.onEvent(SwipedCard)
                                }
                            )
                            .bounceBetweenFloat(
                                animationTrigger = currentCardIsScaled,
                                restingValue = 1f,
                                targetValue = 1.03f,
                                durationMillis = ANIMATION_DURATION_MILLIS,
                                onAnimationComplete = { eventReceiver.onEvent(BounceReset) }
                            ),
                        isCardFlipped = currentCardIsFlipped,
                        onCardClicked = { eventReceiver.onEvent(ClickedCard) },
                        frontContent = flashcard.front,
                        backContent = flashcard.back,
                        flipDurationMillis = ANIMATION_DURATION_MILLIS,
                    )
                }
            /*}*/
        }
/*
            FlippableFlashCard(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .bounceBetweenFloat(
                        animationTrigger = currentCardIsScaled,
                        restingValue = 1f,
                        targetValue = 1.03f,
                        durationMillis = ANIMATION_DURATION_MILLIS,
                        onAnimationComplete = { eventReceiver.onEvent(BounceReset) }
                    ),
                isCardFlipped = currentCardIsFlipped,
                onCardClicked = { eventReceiver.onEvent(ClickedCard) },
                frontContent = currentCardFront,
                backContent = currentCardBack,
                flipDurationMillis = ANIMATION_DURATION_MILLIS,
            )

*/
    }

}




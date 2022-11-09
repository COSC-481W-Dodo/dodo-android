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
import com.dodo.flashcards.presentation.viewCardsScreen.DummyCard
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent.*
import com.dodo.flashcards.presentation.viewCardsScreen.FlippableFlashCard
import com.dodo.flashcards.presentation.viewCardsScreen.ViewCardsViewEvent
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class, ExperimentalSwipeableCardApi::class)
@Composable
fun CardsLoaded(
    currentCard: Flashcard?,
    nextCard: Flashcard?,
    currentCardIsFlipped: Boolean,
    currentCardIsScaled: Boolean,
    hasPreviousCard: Boolean,
    eventReceiver: EventReceiver<ViewCardsViewEvent>,
) {

    val ANIMATION_DURATION_MILLIS = 350;
    val cardBackgroundColor = MaterialTheme.colors.primaryVariant
    val cardTextColor = MaterialTheme.colors.onBackground
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

        nextCard?.let {
            DummyCard(
                modifier = Modifier
                    .fillMaxSize(0.88f)
                    .align(Alignment.Center),
                frontContent = it.front,
                backgroundColor = cardBackgroundColor,
                textColor = cardTextColor
            )
        }


        currentCard?.let {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Center)
            ) {

                val swipeState = rememberSwipeState(
                    maxWidth = constraints.maxWidth.toFloat(),
                    maxHeight = constraints.maxHeight.toFloat()
                )


                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .swipe(
                            state = swipeState,
                            onDragAccepted = { eventReceiver.onEvent(SwipedCard) },
                            isFlipped = currentCardIsFlipped,
                            onClick = { eventReceiver.onEvent(ClickedCard) }
                        )
                ) {
                    FlippableFlashCard(
                        modifier = Modifier
                            .fillMaxSize(.88f)
                            .align(Alignment.Center),
/*
                            .bounceBetweenFloat(
                                animationTrigger = currentCardIsScaled,
                                restingValue = 1f,
                                targetValue = 1.03f,
                                durationMillis = ANIMATION_DURATION_MILLIS,
                                onAnimationComplete = { eventReceiver.onEvent(BounceReset) }
                            ),
*/
                        isCardFlipped = currentCardIsFlipped,
                        frontContent = it.front,
                        backContent = it.back,
                        flipDurationMillis = ANIMATION_DURATION_MILLIS,
                        backgroundColor = cardBackgroundColor,
                        textColor = cardTextColor
                    )
                }

                LaunchedEffect(key1 = currentCard) {
                    if (currentCard == nextCard) {
                        println("here current card is equal to next card")
                        swipeState.snapBack(this)
                        eventReceiver.onEvent(SwipedCardReset)
                    } else {
                        println("current card is not equal to next card")
                    }
                }
            }
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





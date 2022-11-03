package com.dodo.flashcards.presentation.common.previews.ViewCardsScreenPreview.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dodo.flashcards.presentation.theme.FlashcardsAppTheme

class ComposablePreviewStarter {
    private val eventTrigger = mutableStateOf(false);
    @Preview
    @Composable
    fun ComposablePreviewStarter(){
        val colors = MaterialTheme.colors;
        FlashcardsAppTheme(false) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.background)
            ){

            }
        }
    }

    private fun onCardClicked() {
        this@ComposablePreviewStarter.apply {
            eventTrigger.value = !eventTrigger.value
        }
    }
}

package com.dodo.flashcards.presentation.viewTagsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import com.dodo.flashcards.presentation.common.ScreenBackground

@Composable
fun ViewTagsScreen(viewModel: ViewTagsViewModel) {
    ScreenBackground {
        viewModel.viewState.collectAsState().value?.apply {
            Column {
                tags.forEachIndexed { index, tag ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(tag.value)
                        Checkbox(
                            checked = selectedIndices.contains(index),
                            onCheckedChange = {}
                        )
                    }
                }
                Button(
                    onClick = {}
                ) {
                    Text("View Flashcards")
                }
            }
        }
    }
}
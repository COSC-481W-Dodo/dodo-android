package com.dodo.flashcards.presentation.viewTagsScreen.subscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dodo.flashcards.architecture.EventReceiver
import com.dodo.flashcards.domain.models.Tag
import com.dodo.flashcards.presentation.viewTagsScreen.ViewTagsViewEvent
import com.dodo.flashcards.presentation.viewTagsScreen.ViewTagsViewEvent.*

@Composable
fun LoadedTags(
    tags: List<Tag>,
    selectedIndices: Set<Int>,
    eventReceiver: EventReceiver<ViewTagsViewEvent>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        tags.forEachIndexed { index, tag ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(tag.value)
                Checkbox(
                    checked = selectedIndices.contains(index),
                    onCheckedChange = {
                        eventReceiver.onEvent(ToggledTag(index))
                    }
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
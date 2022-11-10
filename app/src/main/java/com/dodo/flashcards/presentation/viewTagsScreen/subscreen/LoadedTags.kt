package com.dodo.flashcards.presentation.viewTagsScreen.subscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    tags.forEachIndexed { index, tag ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = selectedIndices.contains(index),
                onCheckedChange = {
                    eventReceiver.onEvent(ToggledTag(index))
                }
            )
            Text(tag.value)
        }
        Divider(Modifier.fillMaxWidth())
    }
}
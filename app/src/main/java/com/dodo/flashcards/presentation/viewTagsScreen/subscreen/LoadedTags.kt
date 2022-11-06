package com.dodo.flashcards.presentation.viewTagsScreen.subscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.R
import com.dodo.flashcards.architecture.EventReceiver
import com.dodo.flashcards.domain.models.Tag
import com.dodo.flashcards.presentation.theme.Typography
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
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top)
    ) {
        Text(
            text = "Select Tags",
            style = Typography.h6,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center
        )
        tags.forEachIndexed { index, tag ->
            Row(
                modifier = Modifier.fillMaxWidth(),
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
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = {
                    eventReceiver.onEventDebounced(ClickedViewCards)
                }
            ) {
                Text("View Flashcards")
            }
            TextButton(onClick = { eventReceiver.onEventDebounced(ClickedNavigateUp) }) {
                Text("Return")
            }
        }
    }
}
package com.dodo.flashcards.presentation.viewTagsScreen.subscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dodo.flashcards.architecture.EventReceiver
import com.dodo.flashcards.domain.models.Tag
import com.dodo.flashcards.presentation.theme.Typography
import com.dodo.flashcards.presentation.viewTagsScreen.ViewTagsViewEvent
import com.dodo.flashcards.presentation.viewTagsScreen.ViewTagsViewEvent.*

@Composable
fun LoadedTags(
    continueButtonEnabled: Boolean,
    errorMessage: String?,
    tags: List<Tag>,
    selectedIndices: Set<Int>,
    eventReceiver: EventReceiver<ViewTagsViewEvent>
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
            },
            enabled = continueButtonEnabled,
        ) {
            Text("View Flashcards")
        }
        errorMessage?.let {
            Text(
                modifier =
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(0.9f),
                style = Typography.subtitle2,
                color = Color(139, 0, 0),
                textAlign = TextAlign.Center,
                text = it
            )
        }
        TextButton(onClick = { eventReceiver.onEventDebounced(ClickedNavigateUp) }) {
            Text("Return")
        }
    }
}
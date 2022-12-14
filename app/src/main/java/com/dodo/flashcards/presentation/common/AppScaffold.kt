package com.dodo.flashcards.presentation.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun AppScaffold(
    topBarTitle: String = "",
    onNavigateUp: (() -> Unit)? = null,
    bottomBar: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = topBarTitle,
                        color = Color.White
                    )
                },
                navigationIcon = onNavigateUp?.let {
                    {
                        IconButton(onClick = it) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                },
                actions = actions,
                backgroundColor = MaterialTheme.colors.secondary
            )
        },
        bottomBar = bottomBar,
        content = content,
    )
}
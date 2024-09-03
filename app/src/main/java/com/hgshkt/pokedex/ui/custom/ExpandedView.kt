package com.hgshkt.pokedex.ui.custom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun ExpandedView(
    modifier: Modifier = Modifier,
    hiddenPart: @Composable () -> Unit,
    visiblePart: @Composable (Modifier) -> Unit,
    expanded: Boolean
) {
    Column(modifier) {
        AnimatedVisibility(
            modifier = modifier,
            visible = expanded,
            enter = expandVertically(tween(600, easing = EaseInOut)),
            exit = shrinkVertically(tween(600, easing = EaseOut))
        ) {
            hiddenPart()
        }
        visiblePart(modifier)
    }
}
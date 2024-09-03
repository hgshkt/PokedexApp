package com.hgshkt.pokedex.ui.custom.image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
fun PokemonImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String = "Pokemon image"
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = url,
//        colorFilter = ColorFilter.tint(Color.Gray),
        contentDescription = contentDescription,
        loading = {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.widthIn(0.dp, 30.dp)
                )
            }
        },
        error = {
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .aspectRatio(1f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.widthIn(0.dp, 30.dp)
                )
            }
        }
    )
}
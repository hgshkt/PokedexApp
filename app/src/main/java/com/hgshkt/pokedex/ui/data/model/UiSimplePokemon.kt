package com.hgshkt.pokedex.ui.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiSimplePokemon(
    var id: Int,
    var name: String,
    var imageUrl: String,
    var types: List<UiType>,
    val stats: UiStats,
    val weight: Int,
    val height: Int
) : Parcelable

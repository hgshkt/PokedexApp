package com.hgshkt.pokedex.ui.screens.list

import com.hgshkt.pokedex.ui.data.model.UiType

data class FilterMenuState(
    val selectedTypes: List<SelectedType>,
    val text: String = "",
    val weightStart: Int = 0,
    val weightEnd: Int = 10000,
    val heightStart: Int = 0,
    val heightEnd: Int = 10000,
    val opened: Boolean = false
) {
    companion object {
        const val maxSelectedTypes = 2
    }

    data class SelectedType(
        val type: UiType,
        var selected: Boolean = true
    )
}


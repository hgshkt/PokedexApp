package com.hgshkt.pokedex.ui.screens.list

import com.hgshkt.pokedex.ui.data.model.UiType

data class FilterMenuState(
    val selectedTypes: List<SelectedType>,
    val text: String = "",
    val weightStart: String = "",
    val weightEnd: String = "",
    val heightStart: String = "",
    val heightEnd: String = "",
    val opened: Boolean = false
) {
    companion object {
        const val MAX_SELECTED_TYPES = 2
        const val WEIGHT_START_DEFAULT = 0
        const val WEIGHT_END_DEFAULT = 9999
        const val HEIGHT_START_DEFAULT = 0
        const val HEIGHT_END_DEFAULT = 9999
    }

    data class SelectedType(
        val type: UiType,
        var selected: Boolean = true
    )
}


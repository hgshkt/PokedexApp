package com.hgshkt.pokedex.ui.screens.list.filter

fun FilterMenuState.accept(type: FilterMenuState.SelectedType): FilterMenuState {
    val selectedCount = selectedTypes.count { it.selected }

    // if no type is selected
    if (selectedCount == selectedTypes.size) {
        return copy(
            selectedTypes = selectedTypes.map {
                it.copy(selected = it.type == type.type)
            }
        )
    } else {

        // if selected type was selected before
        if (selectedTypes.any { it.type == type.type && it.selected }) {
            if (selectedCount == 1) {
                return copy(
                    selectedTypes = selectedTypes.map {
                        it.copy(selected = true)
                    }
                )
            } else {
                return copy(
                    selectedTypes = selectedTypes.map {
                        it.copy(selected = if (it.type == type.type) false else it.selected)
                    }
                )

            }
        }

        // if count of selected types less than maximum
        else if (selectedCount != MAX_SELECTED_TYPES) {
            return copy(
                selectedTypes = selectedTypes.map {
                    it.copy(selected = if (it.type == type.type) true else it.selected)
                }
            )
        } else return this
    }
}
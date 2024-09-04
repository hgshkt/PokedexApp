package com.hgshkt.pokedex.ui.screens.list

import com.hgshkt.pokedex.ui.data.model.UiSimplePokemon

sealed class State {
    data object Loading : State()
    data class Error(val message: String, val handle: () -> Unit) : State()
    sealed class Display(
        open val pokemons: List<UiSimplePokemon>
    ) : State() {
        data class LocalPokemons(override val pokemons: List<UiSimplePokemon>) :
            Display(pokemons)

        data class FilteredPokemons(override val pokemons: List<UiSimplePokemon>) :
            Display(pokemons)
    }
}

data class DownloadingState(
    val count: Int,
    val target: Int,
    val status: String
)
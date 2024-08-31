package com.hgshkt.pokedex.ui.screens.list

import com.hgshkt.domain.useCases.FilterPokemonsUseCase
import com.hgshkt.domain.useCases.GetLocalPokemonsUseCase

data class ListUseCases(
    val getLocalPokemons: GetLocalPokemonsUseCase,
    val filter: FilterPokemonsUseCase
)
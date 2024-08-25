package com.hgshkt.pokedex.ui.screens.list

import com.hgshkt.domain.useCases.FilterPokemonsUseCase
import com.hgshkt.domain.useCases.PagedLoadPokemonsUseCase

data class ListUseCases(
    val pagedLoad: PagedLoadPokemonsUseCase,
    val filter: FilterPokemonsUseCase
)

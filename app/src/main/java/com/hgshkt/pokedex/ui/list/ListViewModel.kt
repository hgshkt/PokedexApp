package com.hgshkt.pokedex.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCases: ListUseCases
) : ViewModel() {

    val pokemons = useCases.loadPokemonsUseCase.execute()
        .cachedIn(viewModelScope)
}
package com.hgshkt.pokedex.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.hgshkt.pokedex.data.mapper.toUi
import com.hgshkt.pokedex.data.model.UiPokemon
import com.hgshkt.pokedex.data.model.UiSimplePokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCases: ListUseCases
) : ViewModel() {

    private val _pokemonsState: MutableStateFlow<PagingData<UiSimplePokemon>> = MutableStateFlow(value = PagingData.empty())
    val pokemonsState: MutableStateFlow<PagingData<UiSimplePokemon>> get() = _pokemonsState

    init {
        viewModelScope.launch {
            useCases.loadPokemonsUseCase.execute()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _pokemonsState.value = pagingData.map { pokemon ->
                        pokemon.toUi()
                    }
                }
        }
    }
}
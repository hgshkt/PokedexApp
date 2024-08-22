package com.hgshkt.pokedex.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.hgshkt.domain.data.Result
import com.hgshkt.domain.useCases.PokemonFilter
import com.hgshkt.pokedex.ui.data.mapper.toUi
import com.hgshkt.pokedex.ui.data.model.UiSimplePokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCases: ListUseCases
) : ViewModel() {

    private val _pokemonsState: MutableStateFlow<PagingData<UiSimplePokemon>> =
        MutableStateFlow(value = PagingData.empty())
    val pokemonsState: MutableStateFlow<PagingData<UiSimplePokemon>> get() = _pokemonsState

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: MutableStateFlow<State> get() = _state

    init {
        viewModelScope.launch {
            useCases.pagedLoad.execute()
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _pokemonsState.value = pagingData.map { pokemon ->
                        pokemon.toUi()
                    }
                }
        }
    }

    fun startFilter(settings: PokemonFilter.Settings) {
        _state.value = State.Loading
        val result = useCases.filter.execute(settings)
        _state.value = when (result) {
            is Result.Success -> State.Loaded(result.value.map { it.toUi() })
            is Result.Error -> State.Error(result.msg)
        }
    }

    sealed class State {
        data object Loading : State()
        data class Error(val message: String) : State()
        data class Loaded(val pokemons: List<UiSimplePokemon>) : State()
    }
}
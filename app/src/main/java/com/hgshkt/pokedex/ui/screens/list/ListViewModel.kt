package com.hgshkt.pokedex.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgshkt.domain.data.Result
import com.hgshkt.pokedex.ui.data.mapper.toDomainSettings
import com.hgshkt.pokedex.ui.data.mapper.toUi
import com.hgshkt.pokedex.ui.data.model.UiSimplePokemon
import com.hgshkt.pokedex.ui.data.model.UiType
import com.hgshkt.pokedex.ui.screens.list.filter.FilterMenuState
import com.hgshkt.pokedex.ui.screens.list.filter.accept
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCases: ListUseCases
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> get() = _state

    private val _filterMenuState: MutableStateFlow<FilterMenuState> = MutableStateFlow(
        FilterMenuState(
            selectedTypes = UiType.entries.map {
                FilterMenuState.SelectedType(it)
            }
        )
    )
    val filterMenuState: StateFlow<FilterMenuState> get() = _filterMenuState

    init {
        getLocalPokemons()
    }

    private fun getLocalPokemons() {
        viewModelScope.launch {
            val flow = useCases.getLocalPokemons.execute()

            _state.value = State.Loaded(emptyList())

            flow.collect { list ->
                if (_state.value is State.Loaded)
                    _state.value = State.Loaded(
                        list.map { pokemon ->
                            pokemon.toUi()
                        }
                    )
            }
        }
    }

    fun updateFilterText(text: String) {
        viewModelScope.launch {
            _filterMenuState.value = _filterMenuState.value.copy(text = text)
        }
    }

    fun updateFilterWeightStart(weight: String) {
        _filterMenuState.value = _filterMenuState.value.copy(
            weightStart = weight
        )
    }

    fun updateFilterWeightEnd(weight: String) {
        _filterMenuState.value = _filterMenuState.value.copy(
            weightEnd = weight
        )
    }

    fun updateFilterHeightStart(height: String) {
        _filterMenuState.value = _filterMenuState.value.copy(
            heightStart = height
        )
    }

    fun updateFilterHeightEnd(height: String) {
        _filterMenuState.value = _filterMenuState.value.copy(
            heightEnd = height
        )
    }

    fun updateFilterPokemonType(type: FilterMenuState.SelectedType) {
        _filterMenuState.value = _filterMenuState.value.accept(type)
    }

    fun startFilter() {
        _state.value = State.Loading
        viewModelScope.launch(Dispatchers.Default) {
            val result = useCases.filter.execute(_filterMenuState.value.toDomainSettings())
            _state.value = when (result) {
                is Result.Success -> State.Loaded(result.value.map { it.toUi() })
                is Result.Error -> State.FilterError(result.msg) {
                    getLocalPokemons()
                    _filterMenuState.value = FilterMenuState(
                        selectedTypes = UiType.entries.map {
                            FilterMenuState.SelectedType(it)
                        },
                        opened = true
                    )
                }
            }
        }
    }

    fun openFilterMenu() {
        _filterMenuState.update {
            it.copy(opened = !it.opened)
        }
    }

    sealed class State {
        data object Loading : State()
        data class Loaded(val pokemons: List<UiSimplePokemon>) : State()
        data class LoadingError(val message: String) : State()
        data class FilterError(val message: String, val handle: () -> Unit) : State()
    }
}
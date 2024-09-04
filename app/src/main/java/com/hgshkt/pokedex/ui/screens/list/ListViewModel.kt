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
import kotlinx.coroutines.flow.takeWhile
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

    private val _downloadingState = MutableStateFlow(DownloadingState(0, 0, ""))
    val downloadingState: StateFlow<DownloadingState> = _downloadingState

    init {
        viewModelScope.launch(Dispatchers.Default) {
            launch(Dispatchers.Default) {
                collectDownloadingStatus()
            }
            collectLocalPokemons()
        }
    }

    private suspend fun collectDownloadingStatus() {
        useCases.downloadingStatus.execute().collect { downloadingState ->
            _downloadingState.value = downloadingState.toUi()
        }
    }

    private suspend fun collectLocalPokemons() {
        val flow = useCases.getLocalPokemons.execute()

        _state.value = State.Display.LocalPokemons(emptyList())

        flow
            .takeWhile {
                _state.value is State.Display.LocalPokemons
            }
            .collect { list ->
                _state.value = State.Display.LocalPokemons(
                    list.map { pokemon ->
                        pokemon.toUi()
                    }
                )
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
                is Result.Success -> State.Display.FilteredPokemons(result.value.map { it.toUi() })
                is Result.Error -> State.Error(result.msg) {
                    launch {
                        collectLocalPokemons()
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
    }

    fun openFilterMenu() {
        _filterMenuState.update {
            it.copy(opened = !it.opened)
        }
    }


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
}
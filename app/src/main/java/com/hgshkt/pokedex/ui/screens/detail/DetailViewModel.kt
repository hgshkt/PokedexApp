package com.hgshkt.pokedex.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgshkt.domain.data.Result
import com.hgshkt.domain.useCases.LoadPokemonByIdUseCase
import com.hgshkt.pokedex.ui.data.mapper.toUi
import com.hgshkt.pokedex.ui.data.model.UiPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val loadPokemonByIdUseCase: LoadPokemonByIdUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State> get() = _state

    fun loadPokemon(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.emit(State.Loading)
            _state.emit(
                when (val res = loadPokemonByIdUseCase.execute(id)) {
                    is Result.Success ->
                        State.Success(res.value.toUi())

                    is Result.Error ->
                        State.Error(res.msg)
                }
            )
        }
    }

    sealed class State {
        data object Loading : State()
        data class Error(val message: String) : State()
        data class Success(val pokemon: UiPokemon) : State()
    }
}
package com.hgshkt.pokedex.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgshkt.domain.data.PokemonResponse
import com.hgshkt.domain.useCases.LoadPokemonByIdUseCase
import com.hgshkt.pokedex.data.mapper.toUi
import com.hgshkt.pokedex.data.model.UiPokemon
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
    private val _state = MutableStateFlow<DetailViewModelState>(DetailViewModelState.Loading)
    val state: StateFlow<DetailViewModelState> get() = _state


    fun loadPokemon(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.emit(
                when (val res = loadPokemonByIdUseCase.execute(id)) {
                    is PokemonResponse.Success ->
                        DetailViewModelState.Success(res.value.toUi())

                    is PokemonResponse.Error ->
                        DetailViewModelState.Error(res.msg)
                }
            )
        }
    }

    sealed class DetailViewModelState {
        data object Loading : DetailViewModelState()
        data class Error(val message: String) : DetailViewModelState()
        data class Success(val pokemon: UiPokemon) : DetailViewModelState()
    }
}
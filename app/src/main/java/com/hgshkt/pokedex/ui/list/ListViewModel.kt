package com.hgshkt.pokedex.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hgshkt.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCases: ListUseCases
) : ViewModel() {

    private val _pokemonsState: MutableStateFlow<PagingData<Pokemon>> = MutableStateFlow(value = PagingData.empty())
    val pokemonsState: MutableStateFlow<PagingData<Pokemon>> get() = _pokemonsState

    init {
        viewModelScope.launch {
            useCases.loadPokemonsUseCase.execute()
                .cachedIn(viewModelScope)
                .collect {
                    _pokemonsState.value = it
                }
        }
    }
}
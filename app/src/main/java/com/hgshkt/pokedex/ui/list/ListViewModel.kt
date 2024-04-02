package com.hgshkt.pokedex.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hgshkt.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val listUseCases: ListUseCases
) : ViewModel() {
    val pokemons: Flow<PagingData<Pokemon>> = Pager(
        PagingConfig(pageSize = 20)
    ) {
        listUseCases.loadPokemonsUseCase.execute()
    }.flow.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}
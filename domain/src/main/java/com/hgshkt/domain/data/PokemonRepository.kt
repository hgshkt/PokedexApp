package com.hgshkt.domain.data

import androidx.paging.PagingData
import com.hgshkt.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemons(): Flow<PagingData<Pokemon>>
}
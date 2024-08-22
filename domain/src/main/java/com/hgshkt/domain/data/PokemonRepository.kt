package com.hgshkt.domain.data

import androidx.paging.PagingData
import com.hgshkt.domain.model.Pokemon
import com.hgshkt.domain.model.SimplePokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemons(): Flow<PagingData<SimplePokemon>>
    suspend fun getPokemon(id: Int): Result<Pokemon>
    fun needToLoad(): Result<List<Int>>
    suspend fun downloadBasePokemons()
    suspend fun downloadPokemonsByIdList(idList: List<Int>)
    fun getAllPokemons(): List<SimplePokemon>
}
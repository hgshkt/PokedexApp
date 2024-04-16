package com.hgshkt.data.repository.local

import androidx.paging.PagingSource
import com.hgshkt.data.repository.local.pokemon.PokemonEntity

interface PokemonLocalStorage {
    fun pokemonsAsPagingSource(): PagingSource<Int, PokemonEntity>
    suspend fun updatePokemonEntities(pokemonEntities: List<PokemonEntity>, refresh: Boolean)
}
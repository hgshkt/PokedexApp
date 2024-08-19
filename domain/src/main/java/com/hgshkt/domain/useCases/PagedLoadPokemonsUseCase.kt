package com.hgshkt.domain.useCases

import androidx.paging.PagingData
import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.model.SimplePokemon
import kotlinx.coroutines.flow.Flow


class PagedLoadPokemonsUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun execute(): Flow<PagingData<SimplePokemon>> = pokemonRepository.getPokemons()

}
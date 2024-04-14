package com.hgshkt.domain.useCases

import androidx.paging.PagingData
import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow


class LoadPokemonsUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun execute(): Flow<PagingData<Pokemon>> = pokemonRepository.getPokemons()

}
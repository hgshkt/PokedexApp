package com.hgshkt.domain.useCases

import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.model.SimplePokemon
import kotlinx.coroutines.flow.Flow


class GetLocalPokemonsUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun execute(): Flow<List<SimplePokemon>> = pokemonRepository.getLocalPokemonsFlow()

}
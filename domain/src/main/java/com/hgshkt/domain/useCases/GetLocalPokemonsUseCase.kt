package com.hgshkt.domain.useCases

import com.hgshkt.domain.data.LocalPokemonRepository
import com.hgshkt.domain.model.SimplePokemon
import kotlinx.coroutines.flow.Flow


class GetLocalPokemonsUseCase(
    private val localPokemonRepository: LocalPokemonRepository
) {
    suspend fun execute(): Flow<List<SimplePokemon>> = localPokemonRepository.getPokemonsFlow()
}
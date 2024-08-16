package com.hgshkt.domain.useCases

import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.data.PokemonResponse
import com.hgshkt.domain.model.Pokemon

class LoadPokemonByIdUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun execute(id: Int): PokemonResponse<Pokemon> {
        return pokemonRepository.getPokemon(id)
    }
}
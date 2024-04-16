package com.hgshkt.data.repository.remote

import com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon.FinalPokemonDTO
import retrofit2.HttpException

interface PokemonRemoteStorage {
    /**
     * Return final remote pokemon models
     */
    suspend fun getPokemons(offset: Int, limit: Int): RSResponse

    /**
     * Remote storage response
     */
    sealed class RSResponse {
        data class PokemonSuccess(val pokemons: List<FinalPokemonDTO>): RSResponse()
        data class Error(val httpException: HttpException): RSResponse()
    }
}
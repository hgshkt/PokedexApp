package com.hgshkt.data.repository.remote

import com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon.FinalPokemonDTO
import com.hgshkt.domain.data.PokemonResponse
import retrofit2.HttpException
import retrofit2.Response

interface PokemonRemoteStorage {
    /**
     * Return final remote pokemon models
     */
    suspend fun getPokemons(offset: Int, limit: Int): RSResponse
    suspend fun getPokemon(id: Int): Response<FinalPokemonDTO>

    /**
     * Remote storage response
     */
    sealed class RSResponse {
        data class PokemonSuccess(val pokemons: List<FinalPokemonDTO>): RSResponse()
        data class Error(val httpException: HttpException): RSResponse()
    }
}
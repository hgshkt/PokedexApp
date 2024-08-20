package com.hgshkt.data.repository.remote

import com.hgshkt.data.repository.remote.pokemon.network.model.RemotePokemonListResponse
import com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon.RemoteCompletePokemon
import retrofit2.HttpException
import retrofit2.Response

interface PokemonRemoteStorage {
    /**
     * Return final remote pokemon models
     */
    suspend fun getPokemons(offset: Int, limit: Int): RSResponse
    suspend fun getPokemon(id: Int): Response<RemoteCompletePokemon>
    suspend fun getBasePokemons(): Response<RemotePokemonListResponse>

    /**
     * Remote storage response
     */
    sealed class RSResponse {
        data class PokemonSuccess(val pokemons: List<RemoteCompletePokemon>): RSResponse()
        data class Error(val httpException: HttpException): RSResponse()
    }
}
package com.hgshkt.data.repository.remote.pokemon.network

import com.hgshkt.data.repository.remote.pokemon.network.model.RemotePokemonListResponse
import com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon.RemoteCompletePokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("/api/v2/pokemon")
    suspend fun pokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<RemotePokemonListResponse>

    @GET("/api/v2/pokemon/{id}")
    suspend fun pokemon(
        @Path("id") id: Int
    ): Response<RemoteCompletePokemon>

    @GET("/api/v2/pokemon/{id}")
    suspend fun pokemon(
        @Path("id") id: String
    ): Response<RemoteCompletePokemon>
}
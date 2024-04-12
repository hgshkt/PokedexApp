package com.hgshkt.data.repository.pokemon.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("/api/v2/pokemon")
    suspend fun pokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<com.hgshkt.data.repository.pokemon.network.model.PokemonResponseDTO>

    @GET("/api/v2/pokemon/{id}")
    suspend fun pokemon(
        @Path("id") id: Int
    ): Response<com.hgshkt.data.repository.pokemon.network.model.finalPokemon.FinalPokemonDTO>

    @GET("/api/v2/pokemon/{id}")
    suspend fun pokemon(
        @Path("id") id: String
    ): Response<com.hgshkt.data.repository.pokemon.network.model.finalPokemon.FinalPokemonDTO>
}
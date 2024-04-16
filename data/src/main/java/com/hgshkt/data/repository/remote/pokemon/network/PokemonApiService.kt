package com.hgshkt.data.repository.remote.pokemon.network

import com.hgshkt.data.repository.remote.pokemon.network.model.PokemonResponseDTO
import com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon.FinalPokemonDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("/api/v2/pokemon")
    suspend fun pokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonResponseDTO>

    @GET("/api/v2/pokemon/{id}")
    suspend fun pokemon(
        @Path("id") id: Int
    ): Response<FinalPokemonDTO>

    @GET("/api/v2/pokemon/{id}")
    suspend fun pokemon(
        @Path("id") id: String
    ): Response<FinalPokemonDTO>
}
package com.hgshkt.data.repository.network

import com.hgshkt.data.repository.network.model.PokemonResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApiService {
    @GET("/pokemon")
    suspend fun pokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonResponseDTO>
}
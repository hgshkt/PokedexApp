package com.hgshkt.domain.data

interface RemotePokemonRepository {
    suspend fun downloadBasePokemons()
    suspend fun downloadPokemon(id: Int)
    suspend fun downloadInfo(id: Int)
}
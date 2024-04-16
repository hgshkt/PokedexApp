package com.hgshkt.data.repository.remote

import com.hgshkt.data.repository.remote.PokemonRemoteStorage.RSResponse
import com.hgshkt.data.repository.remote.pokemon.network.PokemonApiService
import com.hgshkt.data.repository.remote.pokemon.network.model.PokemonFromResponseDTO
import com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon.FinalPokemonDTO
import retrofit2.HttpException

class PokemonRemoteStorageImpl(
    private val pokemonApiService: PokemonApiService
) : PokemonRemoteStorage {
    override suspend fun getPokemons(offset: Int, limit: Int): RSResponse {
        val response = pokemonApiService.pokemons(
            offset = offset,
            limit = limit
        )
        return if (response.isSuccessful) {

            val pokemons = response.body()!!.results
                .mapNotNull { loadFinalPokemon(it) }

            RSResponse.Success(pokemons)

        } else
            RSResponse.Error(HttpException(response))

    }

    private suspend fun loadFinalPokemon(
        pokemonFromResponseDTO: PokemonFromResponseDTO
    ): FinalPokemonDTO? {
        val id = pokemonFromResponseDTO.url?.split('/')?.last { it.isNotEmpty() }!!
        val response = pokemonApiService.pokemon(id)

        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}
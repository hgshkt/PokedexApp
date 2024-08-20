package com.hgshkt.data.repository.remote

import com.hgshkt.data.repository.remote.PokemonRemoteStorage.RSResponse
import com.hgshkt.data.repository.remote.pokemon.network.PokemonApiService
import com.hgshkt.data.repository.remote.pokemon.network.model.RemoteBasePokemon
import com.hgshkt.data.repository.remote.pokemon.network.model.RemotePokemonListResponse
import com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon.RemoteCompletePokemon
import com.hgshkt.data.util.lastParamFromUrl
import retrofit2.HttpException
import retrofit2.Response

class PokemonRemoteStorageImpl(
    private val pokemonApiService: PokemonApiService
) : PokemonRemoteStorage {

    private val baseOffset = 0
    private val allPokemonsLimit = 10000

    override suspend fun getPokemons(offset: Int, limit: Int): RSResponse {
        val response = pokemonApiService.pokemons(
            offset = offset,
            limit = limit
        )
        return if (response.isSuccessful) {

            val pokemons = response.body()!!.results
                .mapNotNull { loadFinalPokemon(it) }

            RSResponse.PokemonSuccess(pokemons)

        } else
            RSResponse.Error(HttpException(response))

    }

    override suspend fun getPokemon(id: Int): Response<RemoteCompletePokemon> {
        return pokemonApiService.pokemon(id)
    }

    override suspend fun getBasePokemons(): Response<RemotePokemonListResponse> {
        return pokemonApiService.pokemons(
            offset = baseOffset,
            limit = allPokemonsLimit
        )
    }

    private suspend fun loadFinalPokemon(
        remoteBasePokemon: RemoteBasePokemon
    ): RemoteCompletePokemon? {
        val id = remoteBasePokemon.url!!.lastParamFromUrl()
        val response = pokemonApiService.pokemon(id)

        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}
package com.hgshkt.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.hgshkt.data.repository.local.PokemonLocalStorage
import com.hgshkt.data.repository.local.pokemon.LocalCompletePokemon
import com.hgshkt.data.repository.mappers.toLocal
import com.hgshkt.data.repository.remote.PokemonRemoteStorage
import com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon.RemoteCompletePokemon
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val pokemonRemoteStorage: PokemonRemoteStorage,
    private val pokemonLocalStorage: PokemonLocalStorage
) : RemoteMediator<Int, LocalCompletePokemon>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocalCompletePokemon>
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.id ?: 0
                }
            }

            val response = pokemonRemoteStorage.getPokemons(offset, 20)

            when (response) {
                is PokemonRemoteStorage.RSResponse.PokemonSuccess -> {
                    return handleSuccessfulResponse(response.pokemons, loadType)
                }

                is PokemonRemoteStorage.RSResponse.Error -> {
                    MediatorResult.Error(response.httpException)
                }
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun handleSuccessfulResponse(
        pokemons: List<RemoteCompletePokemon>, loadType: LoadType
    ): MediatorResult.Success {

        val pokemonEntities = pokemons.map { it.toLocal() }

        pokemons.forEach {
            saveAbilityRefsToLocal(it)
        }

        pokemonLocalStorage.updateCompletePokemons(
            pokemonEntities = pokemonEntities,
            refresh = loadType == LoadType.REFRESH
        )

        return MediatorResult.Success(
            endOfPaginationReached = pokemonEntities.isEmpty()
        )
    }

    private suspend fun saveAbilityRefsToLocal(remoteCompletePokemon: RemoteCompletePokemon) {
        remoteCompletePokemon.abilities.forEach {
            val abilityId = it.ability?.url?.split('/')?.last { it.isNotEmpty() }!!
            pokemonLocalStorage.saveAbilityRef(remoteCompletePokemon.id!!, abilityId.toInt())
        }
    }
}
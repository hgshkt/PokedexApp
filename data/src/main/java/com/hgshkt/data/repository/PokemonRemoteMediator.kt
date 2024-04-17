package com.hgshkt.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.hgshkt.data.repository.local.PokemonLocalStorage
import com.hgshkt.data.repository.local.pokemon.PokemonEntity
import com.hgshkt.data.repository.mappers.toEntity
import com.hgshkt.data.repository.remote.PokemonRemoteStorage
import com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon.FinalPokemonDTO
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val pokemonRemoteStorage: PokemonRemoteStorage,
    private val pokemonLocalStorage: PokemonLocalStorage
) : RemoteMediator<Int, PokemonEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
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

            val rsresponse = pokemonRemoteStorage.getPokemons(offset, 20)

            when (rsresponse) {
                is PokemonRemoteStorage.RSResponse.PokemonSuccess -> {
                    return handleSuccessfulResponse(rsresponse.pokemons, loadType)
                }

                is PokemonRemoteStorage.RSResponse.Error -> {
                    MediatorResult.Error(rsresponse.httpException)
                }
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun handleSuccessfulResponse(
        pokemons: List<FinalPokemonDTO>, loadType: LoadType
    ): MediatorResult.Success {

        val pokemonEntities = pokemons.map { it.toEntity() }

        pokemons.forEach {
            saveAbilityRefsToLocal(it)
        }

        pokemonLocalStorage.updatePokemonEntities(
            pokemonEntities = pokemonEntities,
            refresh = loadType == LoadType.REFRESH
        )

        return MediatorResult.Success(
            endOfPaginationReached = pokemonEntities.isEmpty()
        )
    }

    private suspend fun saveAbilityRefsToLocal(finalPokemonDTO: FinalPokemonDTO) {
        finalPokemonDTO.abilities.forEach {
            val abilityId = it.ability?.url?.split('/')?.last { it.isNotEmpty() }!!
            pokemonLocalStorage.saveAbilityRef(finalPokemonDTO.id!!, abilityId.toInt())
        }
    }
}
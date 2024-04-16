package com.hgshkt.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hgshkt.data.repository.local.pokemon.PokemonDatabase
import com.hgshkt.data.repository.local.pokemon.PokemonEntity
import com.hgshkt.data.repository.mappers.toEntity
import com.hgshkt.data.repository.remote.PokemonRemoteStorage
import com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon.FinalPokemonDTO
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val pokemonDatabase: PokemonDatabase,
    private val pokemonRemoteStorage: PokemonRemoteStorage
) : RemoteMediator<Int, PokemonEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.id ?: 1
                }
            }

            val rsresponse = pokemonRemoteStorage.getPokemons(offset, 20)

            when (rsresponse) {
                is PokemonRemoteStorage.RSResponse.Success -> {
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

        pokemonDatabase.withTransaction {
            if (loadType == LoadType.REFRESH) {
                pokemonDatabase.pokemonDao.deleteAll()
            }
            pokemonDatabase.pokemonDao.upsertAll(pokemonEntities)
        }

        return MediatorResult.Success(
            endOfPaginationReached = pokemonEntities.isEmpty()
        )
    }
}
package com.hgshkt.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.hgshkt.data.repository.pokemon.local.database.PokemonDatabase
import com.hgshkt.data.repository.pokemon.local.model.PokemonEntity
import com.hgshkt.data.repository.pokemon.network.PokemonApiService
import com.hgshkt.data.repository.pokemon.network.model.PokemonFromResponseDTO
import com.hgshkt.data.repository.pokemon.network.model.finalPokemon.FinalPokemonDTO
import com.hgshkt.data.repository.pokemon.toDPokemon
import com.hgshkt.data.repository.pokemon.toEntity
import com.hgshkt.domain.data.model.DPokemon
import com.hgshkt.domain.model.Pokemon
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val pokemonDatabase: PokemonDatabase,
    private val pokemonApiService: PokemonApiService
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

            val response = pokemonApiService.pokemons(
                offset = offset,
                limit = 20
            )
            if(response.isSuccessful) {

                val pokemonEntities: List<PokemonEntity> = response.body()!!.results
                    .mapNotNull { loadFinalPokemon(it)?.toEntity() }

                pokemonDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        pokemonDatabase.dao.deleteAll()
                    }
                    pokemonDatabase.dao.upsertAll(pokemonEntities)
                }
                MediatorResult.Success(
                    endOfPaginationReached = pokemonEntities.isEmpty()
                )
            } else {
                MediatorResult.Error(HttpException(response))
            }
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
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
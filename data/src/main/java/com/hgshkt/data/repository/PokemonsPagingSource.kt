package com.hgshkt.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hgshkt.data.repository.pokemon.network.PokemonApiService
import com.hgshkt.data.repository.pokemon.toDPokemon
import com.hgshkt.domain.data.model.DPokemon
import retrofit2.HttpException

class PokemonsPagingSource(
    private val pokemonService: PokemonApiService
) : PagingSource<Int, DPokemon>() {

    private val pageSize = 20
    private val limit = 20
    private val defaultOffset = 0

    override fun getRefreshKey(state: PagingState<Int, DPokemon>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DPokemon> {
        try {
            val offset = params.key ?: defaultOffset
            val response = pokemonService.pokemons(offset, limit)

            if (response.isSuccessful) {
                val pokemons: List<DPokemon> = response.body()!!.results
                        .mapNotNull { loadFinalPokemon(it) }

                val nextOffset = if (pokemons.isEmpty()) null else offset + pageSize
                val prevOffset = if (offset == defaultOffset) null else offset - pageSize

                return LoadResult.Page(pokemons, prevKey = prevOffset, nextKey = nextOffset)
            } else {
                return LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private suspend fun loadFinalPokemon(pokemonFromResponseDTO: com.hgshkt.data.repository.pokemon.network.model.PokemonFromResponseDTO): DPokemon? {
        val id = pokemonFromResponseDTO.url?.split('/')?.last { it.isNotEmpty() }!!
        val response = pokemonService.pokemon(id)

        if (response.isSuccessful) {
            return response.body()?.toDPokemon()
        }
        return null
    }
}
package com.hgshkt.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hgshkt.data.repository.network.PokemonApiService
import com.hgshkt.domain.model.Pokemon
import retrofit2.HttpException

class PokemonsPagingSource(
    private val pokemonService: PokemonApiService
): PagingSource<Int, Pokemon>() {

    private val pageSize = 20
    private val limit = 20
    private val defaultOffset = 0

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(pageSize) ?: anchorPage.nextKey?.minus(pageSize)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        try {
            val offset = params.key ?: defaultOffset
            val response = pokemonService.pokemons(offset, limit)

            if (response.isSuccessful) {
                val pokemons: List<Pokemon> = response.body()!!.results.map { it.toPokemon() }

                val prevOffset = if (pokemons.isEmpty()) null else offset + pageSize
                val nextOffset = if (offset == defaultOffset) defaultOffset else offset - pageSize

                return LoadResult.Page(pokemons, prevOffset, nextOffset)
            } else {
                return LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
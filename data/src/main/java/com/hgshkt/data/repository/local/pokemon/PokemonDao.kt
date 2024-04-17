package com.hgshkt.data.repository.local.pokemon

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PokemonDao {
    @Upsert
    suspend fun upsertAll(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons")
    fun pagingSource(): PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM pokemons")
    suspend fun deleteAll()
}
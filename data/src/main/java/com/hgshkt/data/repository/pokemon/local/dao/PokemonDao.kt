package com.hgshkt.data.repository.pokemon.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.hgshkt.data.repository.pokemon.local.model.PokemonEntity

@Dao
interface PokemonDao {
    @Upsert
    suspend fun upsertAll(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM pokemonentity")
    fun pagingSource(): PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM pokemonentity")
    suspend fun deleteAll()
}
package com.hgshkt.data.repository.local.pokemon

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PokemonDao {
    @Upsert
    suspend fun upsertAll(pokemons: List<LocalCompletePokemon>)

    @Query("SELECT * FROM pokemons")
    fun pagingSource(): PagingSource<Int, LocalCompletePokemon>

    @Query("DELETE FROM pokemons")
    suspend fun deleteAll()

    @Query("SELECT * FROM pokemons WHERE id=:id")
    fun get(id: Int): List<LocalCompletePokemon?>

    @Upsert
    fun save(pokemon: LocalCompletePokemon)
}
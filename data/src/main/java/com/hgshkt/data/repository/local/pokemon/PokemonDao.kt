package com.hgshkt.data.repository.local.pokemon

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Upsert
    suspend fun upsertAll(pokemons: List<LocalCompletePokemon>)

    @Query("SELECT * FROM pokemons")
    fun getAllAsFlow(): Flow<List<LocalCompletePokemon>>

    @Query("DELETE FROM pokemons")
    suspend fun deleteAll()

    @Query("SELECT * FROM pokemons WHERE id=:id")
    fun get(id: Int): List<LocalCompletePokemon?>

    @Upsert
    fun save(pokemon: LocalCompletePokemon)

    @Query("SELECT * FROM pokemons")
    fun getAll(): List<LocalCompletePokemon>
}
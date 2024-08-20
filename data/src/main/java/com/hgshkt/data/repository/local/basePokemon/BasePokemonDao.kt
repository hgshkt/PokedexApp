package com.hgshkt.data.repository.local.basePokemon

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface BasePokemonDao {
    @Upsert
    suspend fun save(pokemons: List<LocalBasePokemon>)

    @Query("SELECT * FROM base_pokemons WHERE id=:id")
    fun get(id: Int): LocalBasePokemon?

    @Query("SELECT * FROM base_pokemons")
    fun getAll(): List<LocalBasePokemon>?

    @Insert
    fun save(pokemon: LocalBasePokemon)
}
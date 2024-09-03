package com.hgshkt.data.repository.local.basePokemon

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BasePokemonDao {
    @Upsert
    suspend fun save(pokemons: List<LocalBasePokemon>)

    @Query("SELECT * FROM base_pokemons WHERE id=:id")
    fun get(id: Int): LocalBasePokemon

    @Query("SELECT * FROM base_pokemons")
    fun getAll(): List<LocalBasePokemon>

    @Upsert
    fun save(pokemon: LocalBasePokemon)

    @Query("UPDATE base_pokemons SET loaded = true WHERE id = :id")
    fun markAsLoaded(id: Int)

    @Query("UPDATE base_pokemons SET infoLoaded = true WHERE id = :id")
    fun markAsInfoLoaded(id: Int)

    @Query("SELECT loaded FROM base_pokemons WHERE id = :id")
    fun isLoaded(id: Int): Boolean

    @Query("SELECT infoLoaded FROM base_pokemons WHERE id = :id")
    fun isInfoLoaded(id: Int): Boolean

    @Query("SELECT COUNT(*) FROM base_pokemons WHERE loaded = 1")
    fun loadedAsFlow(): Flow<Int>

    @Query("SELECT COUNT(*) FROM base_pokemons WHERE infoLoaded = 1")
    fun infoLoadedAsFlow(): Flow<Int>

    @Query("SELECT COUNT(*) FROM base_pokemons")
    fun count(): Int
}
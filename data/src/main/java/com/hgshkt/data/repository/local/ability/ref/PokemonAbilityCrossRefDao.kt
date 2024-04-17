package com.hgshkt.data.repository.local.ability.ref

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonAbilityCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pokemonAbilityCrossRef: PokemonAbilityCrossRef)

    @Query("SELECT * FROM pokemonabilityrefs WHERE pokemonId = :pokemonId")
    suspend fun getAbilitiesForPokemon(pokemonId: Int): List<PokemonAbilityCrossRef>
}
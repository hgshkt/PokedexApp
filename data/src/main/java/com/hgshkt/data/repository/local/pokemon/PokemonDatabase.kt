package com.hgshkt.data.repository.local.pokemon

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hgshkt.data.repository.local.ability.AbilityDao
import com.hgshkt.data.repository.local.ability.AbilityEntity

@Database(
    entities = [PokemonEntity::class, AbilityEntity::class],
    version = 1
)
abstract class PokemonDatabase: RoomDatabase() {
    abstract val pokemonDao: PokemonDao
    abstract val abilityDao: AbilityDao
}
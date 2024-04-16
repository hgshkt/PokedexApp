package com.hgshkt.data.repository.pokemon.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hgshkt.data.repository.ability.local.database.AbilityDao
import com.hgshkt.data.repository.ability.local.database.AbilityEntity
import com.hgshkt.data.repository.pokemon.local.dao.PokemonDao
import com.hgshkt.data.repository.pokemon.local.model.PokemonEntity

@Database(
    entities = [PokemonEntity::class, AbilityEntity::class],
    version = 1
)
abstract class PokemonDatabase: RoomDatabase() {
    abstract val pokemonDao: PokemonDao
    abstract val abilityDao: AbilityDao
}
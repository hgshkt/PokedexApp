package com.hgshkt.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hgshkt.data.repository.local.ability.AbilityDao
import com.hgshkt.data.repository.local.ability.LocalAbility
import com.hgshkt.data.repository.local.ability.ref.PokemonAbilityCrossRef
import com.hgshkt.data.repository.local.ability.ref.PokemonAbilityCrossRefDao
import com.hgshkt.data.repository.local.pokemon.PokemonDao
import com.hgshkt.data.repository.local.pokemon.LocalCompletePokemon

@Database(
    entities = [LocalCompletePokemon::class, LocalAbility::class, PokemonAbilityCrossRef::class],
    version = 1
)
abstract class PokemonDatabase: RoomDatabase() {
    abstract val pokemonDao: PokemonDao
    abstract val abilityDao: AbilityDao
    abstract val pokemonAbilityCrossRefDao: PokemonAbilityCrossRefDao
}
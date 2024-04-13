package com.hgshkt.data.repository.pokemon.local.database

import androidx.room.Database
import com.hgshkt.data.repository.pokemon.local.dao.PokemonDao
import com.hgshkt.data.repository.pokemon.local.model.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1
)
abstract class PokemonDatabase {
    abstract val dao: PokemonDao
}
package com.hgshkt.data.repository.local.basePokemon

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "base_pokemons")
data class LocalBasePokemon(
    @PrimaryKey
    var id: Int,
    var name: String,
    var url: String,
    var loaded: Boolean = false,
    var abilitiesLoaded: Boolean = false
)
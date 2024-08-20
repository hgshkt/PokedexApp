package com.hgshkt.data.repository.local.pokemonAbilityCrossRef

import androidx.room.Entity

@Entity(
    tableName = "pokemonabilityrefs",
    primaryKeys = ["pokemonId", "abilityId"]
)
data class PokemonAbilityCrossRef(
    var pokemonId: Int,
    var abilityId: Int
)
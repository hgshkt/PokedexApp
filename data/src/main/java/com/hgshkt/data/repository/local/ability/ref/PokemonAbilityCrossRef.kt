package com.hgshkt.data.repository.local.ability.ref

import androidx.room.Entity

@Entity(
    tableName = "pokemonabilityrefs",
    primaryKeys = ["pokemonId", "abilityId"]
)
data class PokemonAbilityCrossRef(
    var pokemonId: Int,
    var abilityId: Int
)
package com.hgshkt.data.repository.local.ability.ref

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.hgshkt.data.repository.local.ability.AbilityEntity
import com.hgshkt.data.repository.local.pokemon.PokemonEntity

@Entity(
    tableName = "pokemonabilityrefs",
    primaryKeys = ["pokemonId", "abilityId"]
)
data class PokemonAbilityCrossRef(
    var pokemonId: Int,
    var abilityId: Int
)
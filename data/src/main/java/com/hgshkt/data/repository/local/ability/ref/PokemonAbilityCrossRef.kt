package com.hgshkt.data.repository.local.ability.ref

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.hgshkt.data.repository.local.ability.AbilityEntity
import com.hgshkt.data.repository.local.pokemon.PokemonEntity

@Entity(
    tableName = "pokemonabilityrefs",
    primaryKeys = ["pokemonId", "abilityId"],
    foreignKeys = [
        ForeignKey(
            entity = PokemonEntity::class,
            parentColumns = ["id"],
            childColumns = ["pokemonId"]
        ),
        ForeignKey(
            entity = AbilityEntity::class,
            parentColumns = ["id"],
            childColumns = ["abilityId"]
        )
    ],
    indices = [Index("pokemonId"), Index("abilityId")]
)
data class PokemonAbilityCrossRef(
    var pokemonId: Int,
    var abilityId: Int
)
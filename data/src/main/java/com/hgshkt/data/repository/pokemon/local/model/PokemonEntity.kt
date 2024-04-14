package com.hgshkt.data.repository.pokemon.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey var id: Int,
    var name: String,
    var imageUrl: String,
    var ability1Url: String?,
    var ability2Url: String?,
    var ability3Url: String?,
    var type1name: String?,
    var type2name: String?,
    val weight: Int,
    val height: Int,
    // stats
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
    val total: Int = hp + attack + defense + specialAttack + specialDefense + speed
)

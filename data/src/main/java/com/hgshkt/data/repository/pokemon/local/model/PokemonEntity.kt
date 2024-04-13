package com.hgshkt.data.repository.pokemon.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    @PrimaryKey var id: Int,
    var name: String,
    var imageUrl: String,
    var abilitiesUrl: List<String>,
    var types: List<String>,
    val weight: Int,
    val height: Int,
    // stats
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
    val total: Int
)

package com.hgshkt.domain.data.model

data class DPokemon(
    var id: Int,
    var name: String,
    var imageUrl: String,
    var abilitiesUrl: List<String>,
    var types: List<DType>,
    val stats: DStats
)

data class DStats(
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
    val total: Int = hp + attack + defense + specialAttack + specialDefense + speed
)
package com.hgshkt.domain.model

data class Pokemon(
    var id: Int,
    var name: String,
    var imageUrl: String?,
    var abilities: List<Ability>,
    var types: List<Type>,
    val stats: Stats,
    val weight: Int,
    val height: Int
)

data class Stats(
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
    val total: Int = hp + attack + defense + specialAttack + specialDefense + speed
)
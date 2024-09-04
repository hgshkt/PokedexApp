package com.hgshkt.domain.model

data class SimplePokemon(
    var id: Int,
    var name: String,
    var imageUrl: String?,
    var types: List<Type>,
    val stats: Stats,
    val weight: Int,
    val height: Int
)
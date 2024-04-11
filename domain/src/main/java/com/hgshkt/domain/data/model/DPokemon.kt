package com.hgshkt.domain.data.model

data class DPokemon(
    var id: Int,
    var name: String,
    var imageUrl: String,
    var abilitiesUrl: List<String>
)

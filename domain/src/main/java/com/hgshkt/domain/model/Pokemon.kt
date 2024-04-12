package com.hgshkt.domain.model

data class Pokemon(
    var id: Int,
    var name: String,
    var imageUrl: String,
    var abilities: List<Ability>,
    var types: List<Type>
)
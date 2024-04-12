package com.hgshkt.data.repository.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName


data class Abilities(
    @SerializedName("ability") var ability: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Ability? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Ability(),
    @SerializedName("is_hidden") var isHidden: Boolean? = null,
    @SerializedName("slot") var slot: Int? = null
)

package com.hgshkt.data.repository.network.model.finalPokemon

import com.google.gson.annotations.SerializedName


data class Abilities(
    @SerializedName("ability") var ability: Ability? = Ability(),
    @SerializedName("is_hidden") var isHidden: Boolean? = null,
    @SerializedName("slot") var slot: Int? = null
)

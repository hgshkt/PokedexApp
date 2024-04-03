package com.hgshkt.data.repository.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class DreamWorld(
    @SerializedName("front_default") var frontDefault: String? = null,
    @SerializedName("front_female") var frontFemale: String? = null
)
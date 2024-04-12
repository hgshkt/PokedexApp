package com.hgshkt.data.repository.ability.network.model.ability

import com.google.gson.annotations.SerializedName

data class SimplePokemon(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)

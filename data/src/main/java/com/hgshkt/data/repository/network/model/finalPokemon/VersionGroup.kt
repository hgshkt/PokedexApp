package com.hgshkt.data.repository.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class VersionGroup(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)

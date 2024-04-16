package com.hgshkt.data.repository.remote.ability.network.model.ability

import com.google.gson.annotations.SerializedName

data class FlavorTextEntry (
    @SerializedName("flavor_text") val flavorText : String,
    @SerializedName("language") val language : Language,
    @SerializedName("version_group") val versionGroup : VersionGroup
)
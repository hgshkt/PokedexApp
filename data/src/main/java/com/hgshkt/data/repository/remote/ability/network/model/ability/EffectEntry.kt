package com.hgshkt.data.repository.remote.ability.network.model.ability

import com.google.gson.annotations.SerializedName

data class EffectEntry (
    @SerializedName("effect") val effect : String,
    @SerializedName("language") val language : Language,
    @SerializedName("short_effect") val shortEffect : String
)
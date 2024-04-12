package com.hgshkt.data.repository.ability.network.model.ability

import com.google.gson.annotations.SerializedName

data class SimpleEffectEntries(
    @SerializedName("effect") val effect: String,
    @SerializedName("language") val language: Language,
)

package com.hgshkt.data.repository.network.model.ability

import com.google.gson.annotations.SerializedName

data class EffectEntries (
	@SerializedName("effect") val effect : String,
	@SerializedName("language") val language : Language,
	@SerializedName("short_effect") val shortEffect : String
)
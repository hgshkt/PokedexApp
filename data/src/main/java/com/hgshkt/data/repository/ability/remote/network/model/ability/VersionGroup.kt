package com.hgshkt.data.repository.ability.remote.network.model.ability

import com.google.gson.annotations.SerializedName

data class VersionGroup (
	@SerializedName("name") val name : String,
	@SerializedName("url") val url : String
)
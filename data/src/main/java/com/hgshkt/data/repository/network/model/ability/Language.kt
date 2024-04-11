package com.hgshkt.data.repository.network.model.ability

import com.google.gson.annotations.SerializedName

data class Language (
	@SerializedName("name") val name : String,
	@SerializedName("url") val url : String
)
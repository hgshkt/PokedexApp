package com.hgshkt.data.repository.network.model.ability

import com.google.gson.annotations.SerializedName


data class Names (
	@SerializedName("language") val language : Language,
	@SerializedName("name") val name : String
)
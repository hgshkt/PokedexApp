package com.hgshkt.data.repository.network.model.ability

import com.google.gson.annotations.SerializedName

data class FlavorTextEntries (
	@SerializedName("flavor_text") val flavorText : String,
	@SerializedName("language") val language : Language,
	@SerializedName("version_group") val versionGroup : VersionGroup
)
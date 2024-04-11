package com.hgshkt.data.repository.network.model.ability

import com.google.gson.annotations.SerializedName

data class EffectChange (

	@SerializedName("effect_entries") val effectEntries : List<SimpleEffectEntries>,
	@SerializedName("version_group") val versionGroup : VersionGroup
)
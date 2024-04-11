package com.hgshkt.data.repository.network.model.ability

import com.google.gson.annotations.SerializedName

data class ResponseAbility(
    @SerializedName("effect_changes") val effectChanges : List<String>,
    @SerializedName("effect_entries") val effectEntries : List<EffectEntries>,
    @SerializedName("flavor_text_entries") val flavorTextEntries : List<FlavorTextEntries>,
    @SerializedName("generation") val generation : Generation,
    @SerializedName("id") val id : Int,
    @SerializedName("is_main_series") val isMainSeries : Boolean,
    @SerializedName("name") val name : String,
    @SerializedName("names") val names : List<Names>,
    @SerializedName("pokemon") val pokemons : List<Pokemon>
)

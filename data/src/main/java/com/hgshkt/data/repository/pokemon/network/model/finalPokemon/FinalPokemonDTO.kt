package com.hgshkt.data.repository.pokemon.network.model.finalPokemon

import com.google.gson.annotations.SerializedName

data class FinalPokemonDTO(
    @SerializedName("abilities") var abilities: ArrayList<com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Abilities> = arrayListOf(),
    @SerializedName("base_experience") var baseExperience: Int? = null,
    @SerializedName("cries") var cries: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Cries? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Cries(),
    @SerializedName("forms") var forms: ArrayList<com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Forms> = arrayListOf(),
    @SerializedName("game_indices") var gameIndices: ArrayList<com.hgshkt.data.repository.pokemon.network.model.finalPokemon.GameIndices> = arrayListOf(),
    @SerializedName("height") var height: Int? = null,
//    @SerializedName("held_items") var heldItems: ArrayList<String> = arrayListOf(),
    @SerializedName("id") var id: Int? = null,
    @SerializedName("is_default") var isDefault: Boolean? = null,
    @SerializedName("location_area_encounters") var locationAreaEncounters: String? = null,
    @SerializedName("moves") var moves: ArrayList<com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Moves> = arrayListOf(),
    @SerializedName("name") var name: String? = null,
    @SerializedName("order") var order: Int? = null,
//    @SerializedName("past_abilities") var pastAbilities: ArrayList<String> = arrayListOf(),
//    @SerializedName("past_types") var pastTypes: ArrayList<String> = arrayListOf(),
    @SerializedName("species") var species: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Species? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Species(),
    @SerializedName("sprites") var sprites: com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Sprites? = com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Sprites(),
    @SerializedName("stats") var stats: ArrayList<com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Stats> = arrayListOf(),
    @SerializedName("types") var types: ArrayList<com.hgshkt.data.repository.pokemon.network.model.finalPokemon.Types> = arrayListOf(),
    @SerializedName("weight") var weight: Int? = null
)

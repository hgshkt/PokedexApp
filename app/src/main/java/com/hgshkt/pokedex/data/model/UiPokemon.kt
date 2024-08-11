package com.hgshkt.pokedex.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiPokemon(
    var id: Int,
    var name: String,
    var imageUrl: String,
    var abilities: List<UiPokemonAbility>,
    var types: List<UiPokemonType>,
    val stats: UiStats,
    val weight: Int,
    val height: Int
) : Parcelable

@Parcelize
data class UiStats(
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
    val total: Int = hp + attack + defense + specialAttack + specialDefense + speed
) : Parcelable

enum class UiPokemonType {
    NORMAL,
    FIRE,
    WATER,
    ELECTRIC,
    GRASS,
    ICE,
    FIGHTING,
    POISON,
    GROUND,
    FLYING,
    PHYSIC,
    BUG,
    ROCK,
    GHOST,
    DRAGON,
    DARK,
    STEEL,
    FAIRY
}

@Parcelize
data class UiPokemonAbility(
    var id: Int,
    var name: String,
    var effect: String
) : Parcelable
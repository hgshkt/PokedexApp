package com.hgshkt.pokedex.ui.data.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiPokemon(
    var id: Int,
    var name: String,
    var imageUrl: String?,
    var abilities: List<UiPokemonAbility?>,
    var types: List<UiType>,
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

enum class UiType(val text: String, val backgroundColor: Color, val textColor: Color) {
    GROUND("GROUND", Color(0xFF836E64), Color(0xFFFFFFFF)),
    NORMAL("NORMAL", Color(0xFFAEA392), Color(0xFFFFFFFF)),
    ROCK("ROCK", Color(0xFF767676), Color(0xFFFFFFFF)),
    STEEL("STEEL", Color(0xFF9F9F9F), Color(0xFFFFFFFF)),
    ICE("ICE", Color(0xFFA8D3EB), Color(0xFFFFFFFF)),
    WATER("WATER", Color(0xFF70A1D1), Color(0xFFFFFFFF)),
    GRASS("GRASS", Color(0xFF72D3B4), Color(0xFFFFFFFF)),
    FLYING("FLYING", Color(0xFF71C3CB), Color(0xFFFFFFFF)),
    DARK("DARK", Color(0xFF7470D1), Color(0xFFFFFFFF)),
    BUG("BUG", Color(0xFFC8DFA7), Color(0xFFFFFFFF)),
    ELECTRIC("ELECTRIC", Color(0xFFFAE461), Color(0xFFFFFFFF)),
    POISON("POISON", Color(0xFFA270D1), Color(0xFFFFFFFF)),
    DRAGON("DRAGON", Color(0xFFF3726C), Color(0xFFFFFFFF)),
    FIRE("FIRE", Color(0xFFEF9566), Color(0xFFFFFFFF)),
    PHYSIC("PHYSIC", Color(0xFFD170C5), Color(0xFFFFFFFF)),
    FIGHTING("FIGHTING", Color(0xFFD17072), Color(0xFFFFFFFF)),
    GHOST("GHOST", Color(0xFFBCA5D4), Color(0xFFFFFFFF)),
    FAIRY("FAIRY", Color(0xFFF4C9DA), Color(0xFFFFFFFF)),
}

@Parcelize
data class UiPokemonAbility(
    var id: Int,
    var name: String,
    var effect: String
) : Parcelable
package com.hgshkt.pokedex.data.model

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiPokemon(
    var id: Int,
    var name: String,
    var imageUrl: String,
    var abilities: List<UiPokemonAbility>,
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
    NORMAL("NORMAL", Color(0xFFC4A870), Color(0xFFFFFFFF)),
    FIRE("FIRE", Color(0xFFFF8418), Color(0xFFFFFFFF)),
    WATER("WATER", Color(0xFF6A8BEE), Color(0xFFFFFFFF)),
    ELECTRIC("ELECTRIC", Color(0xFFFFE600), Color(0xFFC4A870)),
    GRASS("GRASS", Color(0xFF86EF73), Color(0xFFFFFFFF)),
    ICE("ICE", Color(0xFFA6F2F5), Color(0xFFFFFFFF)),
    FIGHTING("FIGHTING", Color(0xFFFF5757), Color(0xFFFFFFFF)),
    POISON("POISON", Color(0xFFA36CF8), Color(0xFFFFFFFF)),
    GROUND("GROUND", Color(0xFFA8946B), Color(0xFFFFFFFF)),
    FLYING("FLYING", Color(0xFF41E0FC), Color(0xFFFFFFFF)),
    PHYSIC("PHYSIC", Color(0xFFEC3CD4), Color(0xFFFFFFFF)),
    BUG("BUG", Color(0xFFBBE250), Color(0xFFFFFFFF)),
    ROCK("ROCK", Color(0xFFC5B6A0), Color(0xFFFFFFFF)),
    GHOST("GHOST", Color(0xFF8974AF), Color(0xFFFFFFFF)),
    DRAGON("DRAGON", Color(0xFF8E73EF), Color(0xFFFFFFFF)),
    DARK("DARK", Color(0xFF2A3155), Color(0xFFFFFFFF)),
    STEEL("STEEL", Color(0xFF797979), Color(0xFFFFFFFF)),
    FAIRY("FAIRY", Color(0xFFF7A5F2), Color(0xFFFFFFFF))
}

@Parcelize
data class UiPokemonAbility(
    var id: Int,
    var name: String,
    var effect: String
) : Parcelable
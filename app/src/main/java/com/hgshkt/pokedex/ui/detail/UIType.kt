package com.hgshkt.pokedex.ui.detail

import androidx.compose.ui.graphics.Color

import com.hgshkt.domain.model.Type

enum class UIType(val text: String, val backgroundColor: Color, val textColor: Color) {
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

fun Type.toUIType(): UIType {
    return when(this) {
        Type.NORMAL -> UIType.NORMAL
        Type.FIRE -> UIType.FIRE
        Type.WATER -> UIType.WATER
        Type.ELECTRIC -> UIType.ELECTRIC
        Type.GRASS -> UIType.GRASS
        Type.ICE -> UIType.ICE
        Type.FIGHTING -> UIType.FIGHTING
        Type.POISON -> UIType.POISON
        Type.GROUND -> UIType.GROUND
        Type.FLYING -> UIType.FLYING
        Type.PHYSIC -> UIType.PHYSIC
        Type.BUG -> UIType.BUG
        Type.ROCK -> UIType.ROCK
        Type.GHOST -> UIType.GHOST
        Type.DRAGON -> UIType.DRAGON
        Type.DARK -> UIType.DARK
        Type.STEEL -> UIType.STEEL
        Type.FAIRY -> UIType.FAIRY
    }
}
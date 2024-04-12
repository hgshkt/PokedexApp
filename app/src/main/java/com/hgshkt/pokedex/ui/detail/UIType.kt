package com.hgshkt.pokedex.ui.detail

import androidx.compose.ui.graphics.Color

import com.hgshkt.domain.model.Type

enum class UIType(val text: String, val color: Color) {
    NORMAL("NORMAL", Color(0xFFC4A870)),
    FIRE("FIRE", Color(0xFFFF8418)),
    WATER("WATER", Color(0xFF6A8BEE)),
    ELECTRIC("ELECTRIC", Color(0xFFFFE600)),
    GRASS("GRASS", Color(0xFF86EF73)),
    ICE("ICE", Color(0xFFA6F2F5)),
    FIGHTING("FIGHTING", Color(0xFFFF5757)),
    POISON("POISON", Color(0xFFA36CF8)),
    GROUND("GROUND", Color(0xFFA8946B)),
    FLYING("FLYING", Color(0xFF41E0FC)),
    PHYSIC("PHYSIC", Color(0xFFEC3CD4)),
    BUG("BUG", Color(0xFFBBE250)),
    ROCK("ROCK", Color(0xFFC5B6A0)),
    GHOST("GHOST", Color(0xFF8974AF)),
    DRAGON("DRAGON", Color(0xFF8E73EF)),
    DARK("DARK", Color(0xFF2A3155)),
    STEEL("STEEL", Color(0xFF797979)),
    FAIRY("FAIRY", Color(0xFFF7A5F2))
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
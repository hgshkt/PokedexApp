package com.hgshkt.domain.data

sealed class PokemonResponse<T> {
    data class Error<T>(val msg: String): PokemonResponse<T>()
    data class Success<T>(val value: T): PokemonResponse<T>()
}
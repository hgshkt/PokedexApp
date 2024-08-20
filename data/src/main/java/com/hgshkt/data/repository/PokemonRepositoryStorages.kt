package com.hgshkt.data.repository

import com.hgshkt.data.repository.local.ability.AbilityLocalStorage
import com.hgshkt.data.repository.local.basePokemon.BasePokemonLocalStorage
import com.hgshkt.data.repository.local.pokemon.PokemonLocalStorage
import com.hgshkt.data.repository.local.pokemonAbilityCrossRef.PokemonAbilityCrossRefLocalStorage
import com.hgshkt.data.repository.remote.PokemonRemoteStorage
import com.hgshkt.data.repository.remote.ability.AbilityRemoteStorage

data class PokemonRepositoryStorages(
    val local: PokemonRepositoryLocalStorages,
    val remote: PokemonRepositoryRemoteStorages,
)

data class PokemonRepositoryLocalStorages(
    val pokemon: PokemonLocalStorage,
    val basePokemon: BasePokemonLocalStorage,
    val ability: AbilityLocalStorage,
    val pokemonAbilityCrossRef: PokemonAbilityCrossRefLocalStorage,
)

data class PokemonRepositoryRemoteStorages(
    val ability: AbilityRemoteStorage,
    val pokemon: PokemonRemoteStorage
)

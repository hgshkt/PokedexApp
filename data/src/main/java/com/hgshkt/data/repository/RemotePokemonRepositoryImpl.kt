package com.hgshkt.data.repository

import com.hgshkt.data.repository.mappers.toAbility
import com.hgshkt.data.repository.mappers.toLocal
import com.hgshkt.data.repository.remote.pokemon.network.model.finalPokemon.RemoteCompletePokemon
import com.hgshkt.data.util.lastParamFromUrl
import com.hgshkt.domain.data.RemotePokemonRepository

class RemotePokemonRepositoryImpl(
    private val storages: PokemonRepositoryStorages
) : RemotePokemonRepository {
    override suspend fun downloadBasePokemons() {
        val response = storages.remote.pokemon.getBasePokemons()
        if (response.isSuccessful) {
            val localPokemons = response.body()!!.results.map { remotePokemon ->
                remotePokemon.toLocal()
            }
            storages.local.basePokemon.saveBasePokemons(localPokemons)
        }
    }

    override suspend fun downloadPokemon(id: Int): Boolean {
        val response = storages.remote.pokemon.getPokemon(id)
        if (response.isSuccessful) {
            val pokemon = response.body()!!

            saveCrossRefs(pokemon)

            storages.local.pokemon.savePokemon(pokemon.toLocal())

            return true
        }
        return false
    }

    private suspend fun saveCrossRefs(pokemon: RemoteCompletePokemon) {
        pokemon.abilities.forEach { ability ->
            storages.local.pokemonAbilityCrossRef.saveAbilityRef(
                pokemonId = pokemon.id!!,
                abilityId = ability.ability!!.url!!
                    // last url param is ability id
                    .lastParamFromUrl().toInt()
            )
        }
    }

    override suspend fun downloadInfo(id: Int): Boolean {
        return downloadAbilities(id)
    }

    private suspend fun downloadAbilities(id: Int): Boolean {
        storages.local.pokemonAbilityCrossRef.getAbilityRefsForPokemon(id).apply {
            forEach { ref ->
                val ability = storages.remote.ability.getAbility(ref.abilityId)?.toAbility() ?: return false
                storages.local.ability.saveAbility(ability.toLocal())
            }
        }
        return true
    }
}
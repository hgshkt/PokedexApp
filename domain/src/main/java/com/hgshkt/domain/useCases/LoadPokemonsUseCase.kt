package com.hgshkt.domain.useCases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.hgshkt.domain.data.AbilityRemoteRepository
import com.hgshkt.domain.data.PokemonRemoteRepository
import com.hgshkt.domain.data.mapper.toAbility
import com.hgshkt.domain.data.mapper.toPokemon
import com.hgshkt.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LoadPokemonsUseCase(
    private val pokemonRemoteRepository: PokemonRemoteRepository,
    private val abilityRemoteRepository: AbilityRemoteRepository
) {
    fun execute(): Flow<PagingData<Pokemon>> = Pager(
        PagingConfig(pageSize = 20, prefetchDistance = 20)
    ) {
        pokemonRemoteRepository.loadPokemons()
    }
        .flow
        .map { pagingData ->
            pagingData.map { dpokemon ->
                with(dpokemon) {

                    val abilities = abilitiesUrl.map { url ->
                        abilityRemoteRepository.getAbility(url).toAbility()
                    }
                    toPokemon(abilities)

                }
            }
        }

}
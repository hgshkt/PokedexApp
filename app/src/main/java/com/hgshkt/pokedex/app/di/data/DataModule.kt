package com.hgshkt.pokedex.app.di.data

import com.hgshkt.data.filter.PokemonFilterImpl
import com.hgshkt.data.repository.local.ability.AbilityLocalStorage
import com.hgshkt.data.repository.local.ability.AbilityLocalStorageImpl
import com.hgshkt.data.repository.local.basePokemon.BasePokemonLocalStorage
import com.hgshkt.data.repository.local.basePokemon.BasePokemonLocalStorageImpl
import com.hgshkt.data.repository.local.pokemon.PokemonLocalStorage
import com.hgshkt.data.repository.local.pokemon.PokemonLocalStorageImpl
import com.hgshkt.data.repository.local.pokemonAbilityCrossRef.PokemonAbilityCrossRefLocalStorage
import com.hgshkt.data.repository.local.pokemonAbilityCrossRef.PokemonAbilityCrossRefLocalStorageImpl
import com.hgshkt.data.repository.remote.PokemonRemoteStorage
import com.hgshkt.data.repository.remote.PokemonRemoteStorageImpl
import com.hgshkt.data.repository.remote.ability.AbilityRemoteStorage
import com.hgshkt.data.repository.remote.ability.AbilityRemoteStorageImpl
import com.hgshkt.data.repository.remote.pokemon.network.PokemonApiService
import com.hgshkt.domain.useCases.PokemonFilter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataImplModule {
    @Provides
    @Singleton
    fun providePokemonFilterImpl(): PokemonFilterImpl {
        return PokemonFilterImpl()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class Binder {
    @Binds
    abstract fun bindPokemonFilter(
        pokemonFilterImpl: PokemonFilterImpl
    ): PokemonFilter
}
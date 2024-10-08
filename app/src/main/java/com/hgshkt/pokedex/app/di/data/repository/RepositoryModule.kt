package com.hgshkt.pokedex.app.di.data.repository

import com.hgshkt.data.repository.PokemonRemoteMediator
import com.hgshkt.data.repository.PokemonRepositoryImpl
import com.hgshkt.data.repository.PokemonRepositoryLocalStorages
import com.hgshkt.data.repository.PokemonRepositoryRemoteStorages
import com.hgshkt.data.repository.PokemonRepositoryStorages
import com.hgshkt.data.repository.local.PokemonDatabase
import com.hgshkt.data.repository.local.ability.AbilityLocalStorage
import com.hgshkt.data.repository.local.basePokemon.BasePokemonLocalStorage
import com.hgshkt.data.repository.local.pokemon.PokemonLocalStorage
import com.hgshkt.data.repository.local.pokemonAbilityCrossRef.PokemonAbilityCrossRefLocalStorage
import com.hgshkt.data.repository.remote.PokemonRemoteStorage
import com.hgshkt.data.repository.remote.ability.AbilityRemoteStorage
import com.hgshkt.domain.data.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryImplModule {
    @Provides
    @Singleton
    fun providePokemonRepositoryImpl(
        pokemonDatabase: PokemonDatabase,
        pokemonRepositoryStorages: PokemonRepositoryStorages
    ): PokemonRepositoryImpl {
        return PokemonRepositoryImpl(
            pokemonDatabase = pokemonDatabase,
            storages = pokemonRepositoryStorages
        )
    }

    @Provides
    @Singleton
    fun providePokemonRepositoryStorages(
        local: PokemonRepositoryLocalStorages,
        remote: PokemonRepositoryRemoteStorages
    ): PokemonRepositoryStorages {
        return PokemonRepositoryStorages(
            local = local,
            remote = remote
        )
    }

    @Provides
    @Singleton
    fun providePokemonRepositoryLocalStorages(
        pokemonLocalStorage: PokemonLocalStorage,
        basePokemonLocalStorage: BasePokemonLocalStorage,
        pokemonAbilityCrossRefLocalStorage: PokemonAbilityCrossRefLocalStorage,
        abilityLocalStorage: AbilityLocalStorage,
    ): PokemonRepositoryLocalStorages {
        return PokemonRepositoryLocalStorages(
            basePokemon = basePokemonLocalStorage,
            pokemon = pokemonLocalStorage,
            ability = abilityLocalStorage,
            pokemonAbilityCrossRef = pokemonAbilityCrossRefLocalStorage
        )
    }

    @Provides
    @Singleton
    fun providePokemonRepositoryRemoteStorages(
        abilityRemoteStorage: AbilityRemoteStorage,
        pokemonRemoteStorage: PokemonRemoteStorage
    ): PokemonRepositoryRemoteStorages {
        return PokemonRepositoryRemoteStorages(
            pokemon = pokemonRemoteStorage,
            ability = abilityRemoteStorage
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class Binder {
    @Binds
    abstract fun bindPokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository
}
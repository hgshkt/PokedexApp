package com.hgshkt.pokedex.app.di.data.storage

import com.hgshkt.data.repository.local.PokemonDatabase
import com.hgshkt.data.repository.local.ability.AbilityDao
import com.hgshkt.data.repository.local.ability.AbilityLocalStorage
import com.hgshkt.data.repository.local.ability.AbilityLocalStorageImpl
import com.hgshkt.data.repository.local.basePokemon.BasePokemonDao
import com.hgshkt.data.repository.local.basePokemon.BasePokemonLocalStorage
import com.hgshkt.data.repository.local.basePokemon.BasePokemonLocalStorageImpl
import com.hgshkt.data.repository.local.pokemon.PokemonDao
import com.hgshkt.data.repository.local.pokemon.PokemonLocalStorage
import com.hgshkt.data.repository.local.pokemon.PokemonLocalStorageImpl
import com.hgshkt.data.repository.local.pokemonAbilityCrossRef.PokemonAbilityCrossRefDao
import com.hgshkt.data.repository.local.pokemonAbilityCrossRef.PokemonAbilityCrossRefLocalStorage
import com.hgshkt.data.repository.local.pokemonAbilityCrossRef.PokemonAbilityCrossRefLocalStorageImpl
import com.hgshkt.data.repository.remote.PokemonRemoteStorage
import com.hgshkt.data.repository.remote.PokemonRemoteStorageImpl
import com.hgshkt.data.repository.remote.ability.AbilityRemoteStorage
import com.hgshkt.data.repository.remote.ability.AbilityRemoteStorageImpl
import com.hgshkt.data.repository.remote.ability.network.AbilityApiService
import com.hgshkt.data.repository.remote.pokemon.network.PokemonApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageImplModule {


    // Local


    @Provides
    @Singleton
    fun providePokemonsLocalStorageImpl(
        pokemonDao: PokemonDao,
        pokemonDatabase: PokemonDatabase
    ): PokemonLocalStorageImpl {
        return PokemonLocalStorageImpl(
            pokemonDao = pokemonDao,
            pokemonDatabase = pokemonDatabase
        )
    }

    @Provides
    @Singleton
    fun provideAbilityLocalStorageImpl(
        abilityDao: AbilityDao
    ): AbilityLocalStorageImpl {
        return AbilityLocalStorageImpl(
            abilityDao = abilityDao
        )
    }

    @Provides
    @Singleton
    fun provideBasePokemonLocalStorageImpl(
        basePokemonDao: BasePokemonDao
    ): BasePokemonLocalStorageImpl {
        return BasePokemonLocalStorageImpl(
            basePokemonDao = basePokemonDao
        )
    }

    @Provides
    @Singleton
    fun providePokemonAbilityCrossRefLocalStorageImpl(
        pokemonAbilityCrossRefDao: PokemonAbilityCrossRefDao,
    ): PokemonAbilityCrossRefLocalStorageImpl {
        return PokemonAbilityCrossRefLocalStorageImpl(
            pokemonAbilityCrossRefDao = pokemonAbilityCrossRefDao
        )
    }



    // Remote



    @Provides
    @Singleton
    fun providePokemonRemoteStorageImpl(
        pokemonApiService: PokemonApiService
    ): PokemonRemoteStorageImpl {
        return PokemonRemoteStorageImpl(pokemonApiService)
    }

    @Provides
    @Singleton
    fun provideAbilityRemoteStorageImpl(
        abilityApiService: AbilityApiService
    ): AbilityRemoteStorageImpl {
        return AbilityRemoteStorageImpl(
            abilityApiService = abilityApiService
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class Binder {

    // Local


    @Binds
    abstract fun bindPokemonLocalStorage(
        pokemonLocalStorageImpl: PokemonLocalStorageImpl
    ): PokemonLocalStorage

    @Binds
    abstract fun bindBasePokemonLocalStorage(
        basePokemonLocalStorageImpl: BasePokemonLocalStorageImpl
    ): BasePokemonLocalStorage

    @Binds
    abstract fun bindAbilityLocalStorage(
        abilityLocalStorageImpl: AbilityLocalStorageImpl
    ): AbilityLocalStorage

    @Binds
    abstract fun bindPokemonAbilityCrossRefLocalStorage(
        pokemonAbilityCrossRefLocalStorageImpl: PokemonAbilityCrossRefLocalStorageImpl
    ): PokemonAbilityCrossRefLocalStorage


    // Remote


    @Binds
    abstract fun bindPokemonRemoteStorage(
        pokemonRemoteStorageImpl: PokemonRemoteStorageImpl
    ): PokemonRemoteStorage

    @Binds
    abstract fun bindAbilityRemoteStorage(
        abilityRemoteStorageImpl: AbilityRemoteStorageImpl
    ): AbilityRemoteStorage
}
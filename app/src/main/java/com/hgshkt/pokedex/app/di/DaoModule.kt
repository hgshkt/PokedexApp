package com.hgshkt.pokedex.app.di

import com.hgshkt.data.repository.local.pokemon.PokemonDao
import com.hgshkt.data.repository.local.PokemonDatabase
import com.hgshkt.data.repository.local.ability.AbilityDao
import com.hgshkt.data.repository.local.ability.ref.PokemonAbilityCrossRefDao
import com.hgshkt.data.repository.local.basePokemon.BasePokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    @Singleton
    fun provideBasePokemonDao(pokemonDatabase: PokemonDatabase): BasePokemonDao {
        return pokemonDatabase.basePokemonDao
    }

    @Provides
    @Singleton
    fun providePokemonDao(pokemonDatabase: PokemonDatabase): PokemonDao {
        return pokemonDatabase.pokemonDao
    }

    @Provides
    @Singleton
    fun provideAbilityDao(pokemonDatabase: PokemonDatabase): AbilityDao {
        return pokemonDatabase.abilityDao
    }

    @Provides
    @Singleton
    fun providePokemonAbilityCrossRefDao(
        pokemonDatabase: PokemonDatabase
    ): PokemonAbilityCrossRefDao {
        return pokemonDatabase.pokemonAbilityCrossRefDao
    }
}
package com.hgshkt.pokedex.app.di.data

import com.hgshkt.data.filter.PokemonFilterImpl
import com.hgshkt.domain.data.PokemonFilter
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
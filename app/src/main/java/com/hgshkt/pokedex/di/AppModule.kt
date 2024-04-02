package com.hgshkt.pokedex.di

import com.hgshkt.data.repository.PokemonRemoteRepositoryImpl
import com.hgshkt.data.repository.PokemonsPagingSource
import com.hgshkt.data.repository.network.PokemonApiService
import com.hgshkt.data.repository.network.RetrofitClient.pokemonClient
import com.hgshkt.domain.data.PokemonRemoteRepository
import com.hgshkt.domain.useCases.LoadPokemonsUseCase
import com.hgshkt.pokedex.ui.list.ListUseCases
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideListUseCases(
        loadPokemonsUseCase: LoadPokemonsUseCase
    ): ListUseCases {
        return ListUseCases(loadPokemonsUseCase)
    }

    @Provides
    @Singleton
    fun provideLoadPokemonsUseCase(
        pokemonRemoteRepository: PokemonRemoteRepository
    ): LoadPokemonsUseCase {
        return LoadPokemonsUseCase(pokemonRemoteRepository)
    }

    @Provides
    @Singleton
    fun providePokemonRemoteRepositoryImpl(
        pokemonsPagingSource: PokemonsPagingSource
    ): PokemonRemoteRepositoryImpl {
        return PokemonRemoteRepositoryImpl(pokemonsPagingSource)
    }

    @Provides
    @Singleton
    fun providePokemonsPagingSource(): PokemonsPagingSource {
        val pokemonApiService = pokemonClient.create(PokemonApiService::class.java)
        return PokemonsPagingSource(pokemonApiService)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class Binder {
    @Binds
    abstract fun bindPokemonRemoteRepository(
        pokemonRemoteRepositoryImpl: PokemonRemoteRepositoryImpl
    ): PokemonRemoteRepository
}
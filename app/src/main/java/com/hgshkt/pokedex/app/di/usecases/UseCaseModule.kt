package com.hgshkt.pokedex.app.di.usecases

import com.hgshkt.domain.useCases.FilterPokemonsUseCase
import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.useCases.LoadPokemonByIdUseCase
import com.hgshkt.domain.useCases.LoadPokemonsUseCase
import com.hgshkt.domain.useCases.GetLocalPokemonsUseCase
import com.hgshkt.domain.data.PokemonFilter
import com.hgshkt.domain.useCases.DownloadAbilitiesUseCase
import com.hgshkt.pokedex.ui.screens.list.ListUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideListUseCases(
        getLocalPokemonsUseCase: GetLocalPokemonsUseCase,
        filterPokemonsUseCase: FilterPokemonsUseCase
    ): ListUseCases {
        return ListUseCases(getLocalPokemonsUseCase, filterPokemonsUseCase)
    }

    @Provides
    @Singleton
    fun provideLoadPokemonByIdUseCase(
        pokemonRepository: PokemonRepository
    ): LoadPokemonByIdUseCase {
        return LoadPokemonByIdUseCase(pokemonRepository)
    }

    @Provides
    @Singleton
    fun providePagedLoadPokemonsUseCase(
        pokemonRepository: PokemonRepository
    ): GetLocalPokemonsUseCase {
        return GetLocalPokemonsUseCase(pokemonRepository)
    }

    @Provides
    @Singleton
    fun provideLoadPokemonsUseCase(
        pokemonRepository: PokemonRepository
    ): LoadPokemonsUseCase {
        return LoadPokemonsUseCase(pokemonRepository)
    }

    @Provides
    @Singleton
    fun provideDownloadAbilitiesUseCase(
        pokemonRepository: PokemonRepository
    ): DownloadAbilitiesUseCase {
        return DownloadAbilitiesUseCase(pokemonRepository)
    }

    @Provides
    @Singleton
    fun provideFilterPokemonsUseCase(
        pokemonFilter: PokemonFilter,
        pokemonRepository: PokemonRepository
    ): FilterPokemonsUseCase {
        return FilterPokemonsUseCase(pokemonFilter, pokemonRepository)
    }
}
package com.hgshkt.pokedex.app.di.usecases

import com.hgshkt.domain.useCases.FilterPokemonsUseCase
import com.hgshkt.domain.data.LocalPokemonRepository
import com.hgshkt.domain.useCases.LoadPokemonByIdUseCase
import com.hgshkt.domain.useCases.LoadPokemonsUseCase
import com.hgshkt.domain.useCases.GetLocalPokemonsUseCase
import com.hgshkt.domain.data.PokemonFilter
import com.hgshkt.domain.useCases.DownloadDetailInfoUseCase
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
        localPokemonRepository: LocalPokemonRepository
    ): LoadPokemonByIdUseCase {
        return LoadPokemonByIdUseCase(localPokemonRepository)
    }

    @Provides
    @Singleton
    fun provideGetLocalPokemonsUseCase(
        localPokemonRepository: LocalPokemonRepository
    ): GetLocalPokemonsUseCase {
        return GetLocalPokemonsUseCase(localPokemonRepository)
    }

    @Provides
    @Singleton
    fun provideLoadPokemonsUseCase(
        localPokemonRepository: LocalPokemonRepository
    ): LoadPokemonsUseCase {
        return LoadPokemonsUseCase(localPokemonRepository)
    }

    @Provides
    @Singleton
    fun provideDownloadDetailInfoUseCase(
        localPokemonRepository: LocalPokemonRepository
    ): DownloadDetailInfoUseCase {
        return DownloadDetailInfoUseCase(localPokemonRepository)
    }

    @Provides
    @Singleton
    fun provideFilterPokemonsUseCase(
        pokemonFilter: PokemonFilter,
        localPokemonRepository: LocalPokemonRepository
    ): FilterPokemonsUseCase {
        return FilterPokemonsUseCase(pokemonFilter, localPokemonRepository)
    }
}
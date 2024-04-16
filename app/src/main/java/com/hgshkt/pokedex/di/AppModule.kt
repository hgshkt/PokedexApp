package com.hgshkt.pokedex.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.hgshkt.data.repository.PokemonRemoteMediator
import com.hgshkt.data.repository.PokemonRepositoryImpl
import com.hgshkt.data.repository.remote.ability.AbilityRemoteStorageImpl
import com.hgshkt.data.repository.remote.pokemon.PokemonRemoteRepositoryImpl
import com.hgshkt.data.repository.PokemonsPagingSource
import com.hgshkt.data.repository.remote.ability.network.AbilityApiService
import com.hgshkt.data.repository.network.RetrofitClient.pokemonClient
import com.hgshkt.data.repository.local.pokemon.PokemonDatabase
import com.hgshkt.data.repository.remote.pokemon.network.PokemonApiService
import com.hgshkt.domain.data.AbilityRemoteStorage
import com.hgshkt.domain.data.PokemonRemoteRepository
import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.useCases.LoadPokemonsUseCase
import com.hgshkt.pokedex.ui.list.ListUseCases
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePokemonRemoteMediator(
        pokemonDatabase: PokemonDatabase,
        pokemonApiService: PokemonApiService
    ): PokemonRemoteMediator {
        return PokemonRemoteMediator(pokemonDatabase, pokemonApiService)
    }

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
        pokemonRepository: PokemonRepository
    ): LoadPokemonsUseCase {
        return LoadPokemonsUseCase(pokemonRepository)
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
    fun providePokemonRepositoryImpl(
        pokemonRemoteMediator: PokemonRemoteMediator,
        pokemonDatabase: PokemonDatabase,
        abilityRemoteStorage: AbilityRemoteStorage
    ): PokemonRepositoryImpl {
        return PokemonRepositoryImpl(
            remoteMediator = pokemonRemoteMediator,
            pokemonDatabase = pokemonDatabase,
            abilityRemoteStorage = abilityRemoteStorage
        )
    }

    @Provides
    @Singleton
    fun providePokemonDatabase(
        @ApplicationContext context: Context
    ): PokemonDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = PokemonDatabase::class.java,
            name = "pokemons.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAbilityRemoteRepositoryImpl(
        abilityApiService: AbilityApiService
    ): AbilityRemoteStorageImpl {
        return AbilityRemoteStorageImpl(abilityApiService)
    }

    @Provides
    @Singleton
    fun providePokemonApiService(): PokemonApiService {
        return pokemonClient.create(PokemonApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAbilityApiService(): AbilityApiService {
        return pokemonClient.create(AbilityApiService::class.java)
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

    @Binds
    abstract fun bindAbilityRemoteRepository(
        abilityRemoteRepositoryImpl: AbilityRemoteStorageImpl
    ): AbilityRemoteStorage

    @Binds
    abstract fun bindPokemonRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository
}
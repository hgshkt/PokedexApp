package com.hgshkt.pokedex.di

import android.content.Context
import androidx.room.Room
import com.hgshkt.data.repository.PokemonRemoteMediator
import com.hgshkt.data.repository.PokemonRepositoryImpl
import com.hgshkt.data.repository.PokemonsPagingSource
import com.hgshkt.data.repository.local.PokemonDatabase
import com.hgshkt.data.repository.local.PokemonLocalStorage
import com.hgshkt.data.repository.local.PokemonLocalStorageImpl
import com.hgshkt.data.repository.local.ability.AbilityDao
import com.hgshkt.data.repository.local.ability.ref.PokemonAbilityCrossRefDao
import com.hgshkt.data.repository.local.pokemon.PokemonDao
import com.hgshkt.data.repository.network.NetworkInterceptor
import com.hgshkt.data.repository.network.RetrofitClient
import com.hgshkt.data.repository.remote.PokemonRemoteStorage
import com.hgshkt.data.repository.remote.PokemonRemoteStorageImpl
import com.hgshkt.data.repository.remote.ability.AbilityRemoteStorage
import com.hgshkt.data.repository.remote.ability.AbilityRemoteStorageImpl
import com.hgshkt.data.repository.remote.ability.network.AbilityApiService
import com.hgshkt.data.repository.remote.pokemon.PokemonRemoteRepositoryImpl
import com.hgshkt.data.repository.remote.pokemon.network.PokemonApiService
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


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePokemonRemoteMediator(
        pokemonRemoteStorage: PokemonRemoteStorage,
        pokemonLocalStorage: PokemonLocalStorage
    ): PokemonRemoteMediator {
        return PokemonRemoteMediator(pokemonRemoteStorage, pokemonLocalStorage)
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
        abilityRemoteStorage: AbilityRemoteStorage,
        pokemonLocalStorage: PokemonLocalStorage
    ): PokemonRepositoryImpl {
        return PokemonRepositoryImpl(
            remoteMediator = pokemonRemoteMediator,
            pokemonDatabase = pokemonDatabase,
            abilityRemoteStorage = abilityRemoteStorage,
            pokemonLocalStorage = pokemonLocalStorage
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
    fun providePokemonApiService(
        retrofitClient: RetrofitClient
    ): PokemonApiService {
        return retrofitClient.pokemonClient.create(PokemonApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAbilityApiService(
        retrofitClient: RetrofitClient
    ): AbilityApiService {
        return retrofitClient.pokemonClient.create(AbilityApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonsPagingSource(
        pokemonApiService: PokemonApiService
    ): PokemonsPagingSource {
        return PokemonsPagingSource(pokemonApiService)
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(
        networkInterceptor: NetworkInterceptor
    ): RetrofitClient {
        return RetrofitClient(networkInterceptor)
    }

    @Provides
    @Singleton
    fun provideNetworkInterceptor(
        @ApplicationContext context: Context
    ): NetworkInterceptor {
        return NetworkInterceptor(context)
    }

    @Provides
    @Singleton
    fun providePokemonRemoteStorageImpl(
        pokemonApiService: PokemonApiService
    ): PokemonRemoteStorageImpl {
        return PokemonRemoteStorageImpl(pokemonApiService)
    }

    @Provides
    @Singleton
    fun providePokemonsLocalStorageImpl(
        pokemonDao: PokemonDao,
        abilityDao: AbilityDao,
        pokemonAbilityCrossRefDao: PokemonAbilityCrossRefDao,
        pokemonDatabase: PokemonDatabase
    ): PokemonLocalStorageImpl {
        return PokemonLocalStorageImpl(
            pokemonDao = pokemonDao,
            abilityDao = abilityDao,
            pokemonAbilityCrossRefDao = pokemonAbilityCrossRefDao,
            pokemonDatabase = pokemonDatabase
        )
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

    @Binds
    abstract fun bindPokemonLocalStorage(
        pokemonLocalStorageImpl: PokemonLocalStorageImpl
    ): PokemonLocalStorage

    @Binds
    abstract fun bindPokemonRemoteStorage(
        pokemonRemoteStorageImpl: PokemonRemoteStorageImpl
    ): PokemonRemoteStorage
}
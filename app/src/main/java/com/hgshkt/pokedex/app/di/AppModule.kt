package com.hgshkt.pokedex.app.di

import android.content.Context
import androidx.room.Room
import com.hgshkt.data.repository.PokemonRemoteMediator
import com.hgshkt.data.repository.PokemonRepositoryImpl
import com.hgshkt.data.repository.PokemonRepositoryLocalStorages
import com.hgshkt.data.repository.PokemonRepositoryRemoteStorages
import com.hgshkt.data.repository.PokemonRepositoryStorages
import com.hgshkt.data.repository.local.PokemonDatabase
import com.hgshkt.data.repository.local.pokemon.PokemonLocalStorage
import com.hgshkt.data.repository.local.pokemon.PokemonLocalStorageImpl
import com.hgshkt.data.repository.local.ability.AbilityDao
import com.hgshkt.data.repository.local.ability.AbilityLocalStorage
import com.hgshkt.data.repository.local.ability.AbilityLocalStorageImpl
import com.hgshkt.data.repository.local.pokemonAbilityCrossRef.PokemonAbilityCrossRefDao
import com.hgshkt.data.repository.local.basePokemon.BasePokemonDao
import com.hgshkt.data.repository.local.basePokemon.BasePokemonLocalStorage
import com.hgshkt.data.repository.local.basePokemon.BasePokemonLocalStorageImpl
import com.hgshkt.data.repository.local.pokemon.PokemonDao
import com.hgshkt.data.repository.local.pokemonAbilityCrossRef.PokemonAbilityCrossRef
import com.hgshkt.data.repository.local.pokemonAbilityCrossRef.PokemonAbilityCrossRefLocalStorage
import com.hgshkt.data.repository.local.pokemonAbilityCrossRef.PokemonAbilityCrossRefLocalStorageImpl
import com.hgshkt.data.repository.network.NetworkInterceptor
import com.hgshkt.data.repository.network.RetrofitClient
import com.hgshkt.data.repository.remote.PokemonRemoteStorage
import com.hgshkt.data.repository.remote.PokemonRemoteStorageImpl
import com.hgshkt.data.repository.remote.ability.AbilityRemoteStorage
import com.hgshkt.data.repository.remote.ability.AbilityRemoteStorageImpl
import com.hgshkt.data.repository.remote.ability.network.AbilityApiService
import com.hgshkt.data.repository.remote.pokemon.network.PokemonApiService
import com.hgshkt.domain.data.PokemonRepository
import com.hgshkt.domain.useCases.LoadPokemonByIdUseCase
import com.hgshkt.domain.useCases.LoadPokemonsUseCase
import com.hgshkt.domain.useCases.PagedLoadPokemonsUseCase
import com.hgshkt.pokedex.app.NetworkManager
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
        pokemonLocalStorage: PokemonLocalStorage,
        pokemonAbilityCrossRefLocalStorage: PokemonAbilityCrossRefLocalStorage
    ): PokemonRemoteMediator {
        return PokemonRemoteMediator(
            pokemonRemoteStorage = pokemonRemoteStorage,
            pokemonLocalStorage = pokemonLocalStorage,
            pokemonAbilityCrossRefLocalStorage = pokemonAbilityCrossRefLocalStorage
        )
    }

    @Provides
    @Singleton
    fun provideListUseCases(
        pagedLoadPokemonsUseCase: PagedLoadPokemonsUseCase
    ): ListUseCases {
        return ListUseCases(pagedLoadPokemonsUseCase)
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
    ): PagedLoadPokemonsUseCase {
        return PagedLoadPokemonsUseCase(pokemonRepository)
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
    fun providePokemonRepositoryImpl(
        pokemonRemoteMediator: PokemonRemoteMediator,
        pokemonDatabase: PokemonDatabase,
        pokemonRepositoryStorages: PokemonRepositoryStorages
    ): PokemonRepositoryImpl {
        return PokemonRepositoryImpl(
            remoteMediator = pokemonRemoteMediator,
            pokemonDatabase = pokemonDatabase,
            storages = pokemonRepositoryStorages
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
    fun provideAbilityRemoteStorageImpl(
        abilityApiService: AbilityApiService
    ): AbilityRemoteStorageImpl {
        return AbilityRemoteStorageImpl(
            abilityApiService = abilityApiService
        )
    }

    @Provides
    @Singleton
    fun provideBasePokemonStorageImpl(
        basePokemonDao: BasePokemonDao
    ): BasePokemonLocalStorageImpl {
        return BasePokemonLocalStorageImpl(
            basePokemonDao = basePokemonDao
        )
    }

    @Provides
    @Singleton
    fun providePokemonAbilityCrossRefImpl(
        pokemonAbilityCrossRefDao: PokemonAbilityCrossRefDao,
    ): PokemonAbilityCrossRefLocalStorageImpl {
        return PokemonAbilityCrossRefLocalStorageImpl(
            pokemonAbilityCrossRefDao = pokemonAbilityCrossRefDao
        )
    }

    @Provides
    @Singleton
    fun provideNetworkManager(@ApplicationContext context: Context): NetworkManager {
        return NetworkManager(context)
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

    @Binds
    abstract fun bindPokemonRemoteStorage(
        pokemonRemoteStorageImpl: PokemonRemoteStorageImpl
    ): PokemonRemoteStorage
}
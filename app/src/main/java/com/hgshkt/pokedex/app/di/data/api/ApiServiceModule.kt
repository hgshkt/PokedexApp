package com.hgshkt.pokedex.app.di.data.api

import com.hgshkt.data.repository.network.RetrofitClient
import com.hgshkt.data.repository.remote.ability.network.AbilityApiService
import com.hgshkt.data.repository.remote.pokemon.network.PokemonApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {
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
}
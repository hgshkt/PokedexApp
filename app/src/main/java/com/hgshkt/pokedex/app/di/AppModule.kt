package com.hgshkt.pokedex.app.di

import android.content.Context
import androidx.room.Room
import com.hgshkt.data.repository.local.PokemonDatabase
import com.hgshkt.data.repository.network.NetworkInterceptor
import com.hgshkt.data.repository.network.RetrofitClient
import com.hgshkt.pokedex.app.NetworkManager
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
    fun provideNetworkManager(@ApplicationContext context: Context): NetworkManager {
        return NetworkManager(context)
    }
}


package com.hgshkt.pokedex.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class PokemonApplication: Application() {
    @Inject
    lateinit var networkManager: NetworkManager
}
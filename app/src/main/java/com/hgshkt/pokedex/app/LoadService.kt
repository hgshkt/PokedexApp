package com.hgshkt.pokedex.app

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.hgshkt.domain.useCases.DownloadAbilitiesUseCase
import com.hgshkt.domain.useCases.LoadPokemonsUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoadService : Service() {

    @Inject
    lateinit var loadPokemonsUseCase: LoadPokemonsUseCase

    @Inject
    lateinit var loadAbilityUseCase: DownloadAbilitiesUseCase

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CoroutineScope(Dispatchers.IO + SupervisorJob())
            .launch {
                loadPokemonsUseCase.execute()
                loadAbilityUseCase.execute()
            }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
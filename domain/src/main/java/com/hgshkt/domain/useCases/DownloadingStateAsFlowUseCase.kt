package com.hgshkt.domain.useCases

import com.hgshkt.domain.data.LocalPokemonRepository
import kotlinx.coroutines.flow.flow

class DownloadingStateAsFlowUseCase(
    private val repository: LocalPokemonRepository
) {

    suspend fun execute() = flow {
        var target = 0
        repository.pokemonCount().collect {
            target = it
        }
        repository.loadedAsFlow().collect { progress ->
            emit(DownloadingState.Default(count = progress, target = target))
        }
        repository.infoLoadedAsFlow().collect { progress ->
            emit(DownloadingState.DetailInfo(count = progress, target = target))
        }
    }

    sealed class DownloadingState(
        val status: String,
        open val target: Int,
        open val count: Int
    ) {
        data class Default(override val count: Int, override val target: Int): DownloadingState(
            status = "Pokemon are downloading",
            target = target,
            count = count
        )
        data class DetailInfo(override val count: Int, override val target: Int): DownloadingState(
            status = "Details are downloading",
            target = target,
            count = count
        )
    }
}
package com.hgshkt.domain.useCases

import com.hgshkt.domain.data.LocalPokemonRepository
import kotlinx.coroutines.flow.flow

class DownloadingStateAsFlowUseCase(
    private val repository: LocalPokemonRepository
) {

    suspend fun execute() = flow {
        repository.loadedAsFlow().collect { count ->
            emit(DownloadingState.Default(count))
        }
        repository.infoLoadedAsFlow().collect { count ->
            emit(DownloadingState.DetailInfo(count))
        }
    }

    sealed class DownloadingState(
        val status: String,
        open val count: Int
    ) {
        data class Default(override val count: Int): DownloadingState(
            status = "Pokemon are downloading",
            count = count
        )
        data class DetailInfo(override val count: Int): DownloadingState(
            status = "Details are downloading",
            count = count
        )
    }
}
package com.hgshkt.pokedex.ui.data.mapper

import com.hgshkt.domain.useCases.DownloadingStateAsFlowUseCase
import com.hgshkt.pokedex.ui.screens.list.DownloadingState
import com.hgshkt.pokedex.ui.screens.list.ListViewModel

fun DownloadingStateAsFlowUseCase.DownloadingState.toUi(): DownloadingState {
    return DownloadingState(
        status = status,
        count = count,
        target = target
    )
}
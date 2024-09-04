package com.hgshkt.pokedex.ui.data.mapper

import com.hgshkt.domain.useCases.DownloadingStateAsFlowUseCase
import com.hgshkt.pokedex.ui.screens.list.ListViewModel

fun DownloadingStateAsFlowUseCase.DownloadingState.toUi(): ListViewModel.DownloadingState {
    return ListViewModel.DownloadingState(
        status = status,
        count = count,
        target = target
    )
}
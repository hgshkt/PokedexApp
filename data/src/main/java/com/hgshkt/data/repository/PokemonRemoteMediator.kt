package com.hgshkt.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.hgshkt.domain.model.Pokemon

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(

): RemoteMediator<Int, Pokemon>(){
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Pokemon>
    ): MediatorResult {
        TODO("Not yet implemented")
    }
}
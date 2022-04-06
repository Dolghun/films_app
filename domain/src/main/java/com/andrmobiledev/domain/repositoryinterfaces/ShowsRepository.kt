package com.andrmobiledev.domain.repositoryinterfaces

import androidx.paging.PagingData
import com.andrmobiledev.domain.entities.Show
import kotlinx.coroutines.flow.Flow

interface ShowsRepository {
    suspend fun getShows() : Flow<PagingData<Show>>
    suspend fun getShowById(id: Int) : Flow<Show>
}
package com.andrmobiledev.data.repositories

import androidx.paging.PagingData
import androidx.paging.map
import com.andrmobiledev.api.responses.ShowsResponse
import com.andrmobiledev.data.datasources.ShowNetworkDatasource
import com.andrmobiledev.data.toDomain
import com.andrmobiledev.domain.entities.Show
import com.andrmobiledev.domain.repositoryinterfaces.ShowsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ShowsRepositoryImpl @Inject constructor(
    private val networkDatasource: ShowNetworkDatasource,
    private val coroutineDispatcher: CoroutineDispatcher,
) : ShowsRepository {

    override suspend fun getShows(): Flow<PagingData<Show>> =
        networkDatasource.getShows()
            .flow.map { response ->
                response.map {
                    it.toDomain()
                }
            }.flowOn(coroutineDispatcher)

    override suspend fun getShowById(id: Int): Flow<Show> =
        flow {
            emit(networkDatasource.getShowById(id))
        }.map { value: ShowsResponse ->
            value.toDomain()
        }.flowOn(coroutineDispatcher)
}



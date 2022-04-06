package com.andrmobiledev.data.datasources

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.andrmobiledev.api.ApiService
import com.andrmobiledev.api.responses.ShowsResponse
import javax.inject.Inject

class ShowNetworkDatasourceImpl @Inject constructor(private val apiService: ApiService) :
    ShowNetworkDatasource {

    override fun getShows(): Pager<Int, ShowsResponse> =
        Pager(PagingConfig(pageSize = 2)) {
            ShowsPagingSource(apiService)
        }

    override suspend fun getShowById(id: Int): ShowsResponse = apiService.getShowById(id)
}
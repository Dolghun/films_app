package com.andrmobiledev.data.datasources

import androidx.paging.Pager
import com.andrmobiledev.api.responses.ShowsResponse

interface ShowNetworkDatasource {
    fun getShows(): Pager<Int, ShowsResponse>
    suspend fun getShowById(id: Int): ShowsResponse
}
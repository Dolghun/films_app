package com.andrmobiledev.api

import com.andrmobiledev.api.responses.ShowsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(ApiConstants.SHOWS)
    suspend fun getShows(@Query("page") page: Int): List<ShowsResponse>

    @GET(ApiConstants.SHOW_DETAIL)
    suspend fun getShowById(@Path(value = "id") id: Int): ShowsResponse
}
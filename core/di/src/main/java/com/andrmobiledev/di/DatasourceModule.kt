package com.andrmobiledev.di

import com.andrmobiledev.api.ApiService
import com.andrmobiledev.data.datasources.ShowNetworkDatasource
import com.andrmobiledev.data.datasources.ShowNetworkDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatasourceModule {

    @Provides
    fun provideShowNetworkDatasource(apiService: ApiService): ShowNetworkDatasource =
        ShowNetworkDatasourceImpl(apiService)

}
package com.andrmobiledev.di

import com.andrmobiledev.data.datasources.ShowNetworkDatasource
import com.andrmobiledev.data.repositories.ShowsRepositoryImpl
import com.andrmobiledev.domain.repositoryinterfaces.ShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideShowsRepository(
        networkDatasource: ShowNetworkDatasource,
        @Named("IO") dispatcher: CoroutineDispatcher,
    ): ShowsRepository =
        ShowsRepositoryImpl(networkDatasource, dispatcher)

}
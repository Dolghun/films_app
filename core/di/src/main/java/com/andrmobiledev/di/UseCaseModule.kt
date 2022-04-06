package com.andrmobiledev.di

import com.andrmobiledev.domain.repositoryinterfaces.ShowsRepository
import com.andrmobiledev.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun provideShowsUseCase(showsRepository: ShowsRepository): GetShowsUseCase =
        GetShowsUseCase(showsRepository)

    @Provides
    fun provideShowByIdUseCase(showsRepository: ShowsRepository): GetShowByIdUseCase =
        GetShowByIdUseCase(showsRepository)

}
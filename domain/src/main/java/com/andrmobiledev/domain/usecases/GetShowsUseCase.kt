package com.andrmobiledev.domain.usecases

import androidx.paging.PagingData
import com.andrmobiledev.domain.entities.Show
import com.andrmobiledev.domain.repositoryinterfaces.ShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShowsUseCase @Inject constructor(private val showsRepository: ShowsRepository) {

    suspend operator fun invoke(): Flow<PagingData<Show>> =
        showsRepository.getShows()

}
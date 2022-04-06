package com.andrmobiledev.domain.usecases

import com.andrmobiledev.domain.entities.Show
import com.andrmobiledev.domain.repositoryinterfaces.ShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetShowByIdUseCase @Inject constructor(private val showsRepository: ShowsRepository) {

    suspend operator fun invoke(id: Int): Flow<Show> =
        showsRepository.getShowById(id)

}
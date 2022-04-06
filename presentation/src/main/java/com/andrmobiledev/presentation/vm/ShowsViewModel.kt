package com.andrmobiledev.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.andrmobiledev.domain.usecases.GetShowByIdUseCase
import com.andrmobiledev.domain.usecases.GetShowsUseCase
import com.andrmobiledev.presentation.exceptions.mapper.handleExceptions
import com.andrmobiledev.presentation.toDetailUI
import com.andrmobiledev.presentation.toUI
import com.andrmobiledev.presentation.uidata.ShowDetailUI
import com.andrmobiledev.presentation.uidata.ShowListItem
import com.andrmobiledev.presentation.uidata.utils.FeedbackCreator
import com.andrmobiledev.presentation.uidata.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel @Inject constructor(
    private val getShowsUseCase: GetShowsUseCase,
    private val getShowByIdUseCase: GetShowByIdUseCase,
    private val feedbackCreator: FeedbackCreator,
) : ViewModel() {

    private val _showsSharedFlow =
        MutableSharedFlow<PagingData<ShowListItem>>()
    val showsStateFlow = _showsSharedFlow.asSharedFlow()

    private val _showDetailsFlow = MutableSharedFlow<Resource<ShowDetailUI>>()
    val showDetailsFlow = _showDetailsFlow.asSharedFlow()

    fun getShows() {
        viewModelScope.launch {
            getShowsUseCase()
                .cachedIn(viewModelScope)
                .map {
                    it.map { value -> value.toUI() }
                }.collect {
                    _showsSharedFlow.emit(it)
                }
        }
    }

    fun goToShowDetails(id: Int) {
        viewModelScope.launch {
            _showDetailsFlow.emit(Resource.Loading())
            getShowByIdUseCase(id).catch { cause: Throwable ->
                _showDetailsFlow.emit(Resource.Error(getErrorMessage(cause as Exception)))
            }.collect {
                _showDetailsFlow.emit(Resource.Success(it.toDetailUI()))
            }
        }
    }

    fun getErrorMessage(exception: Exception): String {
        val errorType = handleExceptions(exception)
        return feedbackCreator.create(errorType)
    }
}
package dev.wxlf.companieslist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.wxlf.companieslist.domain.mappers.mapToDisplayable
import dev.wxlf.companieslist.domain.usecases.FetchCompanyDetailsUseCase
import dev.wxlf.companieslist.presentation.common.DetailsEvent
import dev.wxlf.companieslist.presentation.common.DetailsViewState
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val fetchCompanyDetailsUseCase: FetchCompanyDetailsUseCase
) : ViewModel() {

    var id: String = ""

    private val _uiState = MutableStateFlow<DetailsViewState>(DetailsViewState.Loading)
    val uiState: StateFlow<DetailsViewState> = _uiState

    fun obtainEvent(event: DetailsEvent) {
        when(event) {
            is DetailsEvent.ScreenShown -> {
                id = event.id
                getDetails(id)
            }
            DetailsEvent.Update -> getDetails(id)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getDetails(id: String) {
        GlobalScope.launch {
            try {
                val details = fetchCompanyDetailsUseCase.execute(id = id).mapToDisplayable()
                _uiState.emit(DetailsViewState.Loaded(details))
            } catch (e: Throwable) {
                _uiState.emit(DetailsViewState.Error(e.localizedMessage.orEmpty()))
            }
        }
    }

}
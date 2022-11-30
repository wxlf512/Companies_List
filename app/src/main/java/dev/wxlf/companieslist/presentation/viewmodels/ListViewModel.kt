package dev.wxlf.companieslist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.wxlf.companieslist.domain.mappers.mapToDisplayable
import dev.wxlf.companieslist.domain.usecases.FetchCompaniesListUseCase
import dev.wxlf.companieslist.presentation.common.CompaniesListEvent
import dev.wxlf.companieslist.presentation.common.CompaniesListViewState
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val fetchCompaniesListUseCase: FetchCompaniesListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CompaniesListViewState>(CompaniesListViewState.Loading)
    val uiState: StateFlow<CompaniesListViewState> = _uiState

    fun obtainEvent(event: CompaniesListEvent) {
        when (event) {
            CompaniesListEvent.ScreenShown -> {
                _uiState.value = CompaniesListViewState.Loading
                getCompaniesList()
            }
            CompaniesListEvent.Update -> getCompaniesList()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getCompaniesList() {
        GlobalScope.launch {
            try {
                val list = fetchCompaniesListUseCase.execute().mapToDisplayable()
                _uiState.emit(CompaniesListViewState.Loaded(list))
            } catch (e: Throwable) {
                _uiState.emit(CompaniesListViewState.Error(e.localizedMessage.orEmpty()))
            }
        }
    }
}
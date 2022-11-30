package dev.wxlf.companieslist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.wxlf.companieslist.domain.usecases.FetchCompaniesListUseCase
import dev.wxlf.companieslist.domain.usecases.FetchCompanyDetailsUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val fetchCompaniesListUseCase: FetchCompaniesListUseCase,
    private val fetchCompanyDetailsUseCase: FetchCompanyDetailsUseCase
) : ViewModel() {
    @OptIn(DelicateCoroutinesApi::class)
    fun test() {
        GlobalScope.launch {
            fetchCompaniesListUseCase.execute()
            fetchCompanyDetailsUseCase.execute("1")
        }
    }
}
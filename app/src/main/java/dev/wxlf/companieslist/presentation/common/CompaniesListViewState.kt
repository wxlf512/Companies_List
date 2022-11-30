package dev.wxlf.companieslist.presentation.common

import dev.wxlf.companieslist.presentation.models.DisplayableCompanyItem

sealed class CompaniesListViewState {
    object Loading: CompaniesListViewState()
    data class Loaded(val companiesList: List<DisplayableCompanyItem>) : CompaniesListViewState()
    data class Error(val msg: String): CompaniesListViewState()
}

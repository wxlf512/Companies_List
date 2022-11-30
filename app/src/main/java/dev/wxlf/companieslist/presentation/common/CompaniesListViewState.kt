package dev.wxlf.companieslist.presentation.common

import dev.wxlf.companieslist.presentation.models.DisplayableCompanyItemModel

sealed class CompaniesListViewState {
    object Loading: CompaniesListViewState()
    data class Loaded(val companiesList: List<DisplayableCompanyItemModel>) : CompaniesListViewState()
    data class Error(val msg: String): CompaniesListViewState()
}

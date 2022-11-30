package dev.wxlf.companieslist.presentation.common

sealed class CompaniesListEvent {
    object ScreenShown: CompaniesListEvent()
    object Update: CompaniesListEvent()
}

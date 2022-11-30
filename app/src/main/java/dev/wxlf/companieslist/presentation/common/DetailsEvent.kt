package dev.wxlf.companieslist.presentation.common

sealed class DetailsEvent {
    data class ScreenShown(val id: String): DetailsEvent()
    object Update: DetailsEvent()
}

package dev.wxlf.companieslist.presentation.common

import dev.wxlf.companieslist.presentation.models.DisplayableDetailsModel

sealed class DetailsViewState {
    object Loading: DetailsViewState()
    data class Loaded(val details: DisplayableDetailsModel) : DetailsViewState()
    data class Error(val msg: String): DetailsViewState()
}

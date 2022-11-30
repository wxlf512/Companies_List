package dev.wxlf.companieslist.domain.mappers

import dev.wxlf.companieslist.data.entities.CompanyDetailsEntity
import dev.wxlf.companieslist.presentation.models.DisplayableDetailsModel

fun List<CompanyDetailsEntity>.mapToDisplayable() =
    DisplayableDetailsModel(
        name = this[0].name,
        img = this[0].img,
        description = this[0].description,
        lat = this[0].lat,
        lon = this[0].lon,
        www = this[0].www,
        phone = this[0].phone
    )
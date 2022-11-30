package dev.wxlf.companieslist.domain.mappers

import dev.wxlf.companieslist.data.entities.CompanyEntity
import dev.wxlf.companieslist.presentation.models.DisplayableCompanyItem

fun List<CompanyEntity>.mapToDisplayable(): List<DisplayableCompanyItem> =
    this.map { it.mapToDisplayable() }

fun CompanyEntity.mapToDisplayable(): DisplayableCompanyItem =
    DisplayableCompanyItem(id = id, name = name, img = img)
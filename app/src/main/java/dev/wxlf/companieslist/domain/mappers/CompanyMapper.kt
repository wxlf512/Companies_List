package dev.wxlf.companieslist.domain.mappers

import dev.wxlf.companieslist.data.entities.CompanyEntity
import dev.wxlf.companieslist.presentation.models.DisplayableCompanyItemModel

fun List<CompanyEntity>.mapToDisplayable(): List<DisplayableCompanyItemModel> =
    this.map { it.mapToDisplayable() }

fun CompanyEntity.mapToDisplayable(): DisplayableCompanyItemModel =
    DisplayableCompanyItemModel(id = id, name = name, img = img)
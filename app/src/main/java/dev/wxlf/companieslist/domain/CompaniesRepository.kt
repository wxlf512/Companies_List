package dev.wxlf.companieslist.domain

import dev.wxlf.companieslist.data.entities.CompanyDetailsEntity
import dev.wxlf.companieslist.data.entities.CompanyEntity

interface CompaniesRepository {

    suspend fun fetchCompaniesList(): List<CompanyEntity>
    suspend fun fetchDetailsById(id: String): List<CompanyDetailsEntity>
}
package dev.wxlf.companieslist.data.datasources

import dev.wxlf.companieslist.data.entities.CompanyDetailsEntity
import dev.wxlf.companieslist.data.entities.CompanyEntity

interface CompaniesRemoteDataSource {
    suspend fun loadCompaniesList(): List<CompanyEntity>

    suspend fun loadDetailsById(id: String): List<CompanyDetailsEntity>
}
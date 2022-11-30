package dev.wxlf.companieslist.data.datasources.remote

import dev.wxlf.companieslist.data.datasources.CompaniesRemoteDataSource
import dev.wxlf.companieslist.data.entities.CompanyDetailsEntity
import dev.wxlf.companieslist.data.entities.CompanyEntity
import dev.wxlf.companieslist.data.network.CompaniesApi

class RetrofitCompaniesDataSource(private val companiesApi: CompaniesApi) : CompaniesRemoteDataSource {
    override suspend fun loadCompaniesList(): List<CompanyEntity> =
        companiesApi.loadCompaniesList()

    override suspend fun loadDetailsById(id: String): List<CompanyDetailsEntity> =
        companiesApi.loadDetailsById(id)
}
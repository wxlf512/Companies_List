package dev.wxlf.companieslist.data

import dev.wxlf.companieslist.data.datasources.CompaniesRemoteDataSource
import dev.wxlf.companieslist.data.entities.CompanyDetailsEntity
import dev.wxlf.companieslist.data.entities.CompanyEntity
import dev.wxlf.companieslist.domain.CompaniesRepository

class CompaniesRepositoryImpl(private val remote: CompaniesRemoteDataSource) : CompaniesRepository {
    override suspend fun fetchCompaniesList(): List<CompanyEntity> =
        remote.loadCompaniesList()


    override suspend fun fetchDetailsById(id: String): List<CompanyDetailsEntity> =
        remote.loadDetailsById(id)
}
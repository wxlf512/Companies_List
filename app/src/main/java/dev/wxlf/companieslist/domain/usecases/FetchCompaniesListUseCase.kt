package dev.wxlf.companieslist.domain.usecases

import dev.wxlf.companieslist.domain.CompaniesRepository

class FetchCompaniesListUseCase(private val companiesRepository: CompaniesRepository) {

    suspend fun execute() = companiesRepository.fetchCompaniesList()
}
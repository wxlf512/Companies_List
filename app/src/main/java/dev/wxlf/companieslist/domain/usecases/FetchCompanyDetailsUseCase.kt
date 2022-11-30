package dev.wxlf.companieslist.domain.usecases

import dev.wxlf.companieslist.domain.CompaniesRepository

class FetchCompanyDetailsUseCase(private val companiesRepository: CompaniesRepository) {

    suspend fun execute(id: String) = companiesRepository.fetchDetailsById(id)
}
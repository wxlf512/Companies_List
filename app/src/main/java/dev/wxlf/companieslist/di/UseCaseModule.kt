package dev.wxlf.companieslist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.wxlf.companieslist.domain.CompaniesRepository
import dev.wxlf.companieslist.domain.usecases.FetchCompaniesListUseCase
import dev.wxlf.companieslist.domain.usecases.FetchCompanyDetailsUseCase

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideFetchCompaniesListUseCase(companiesRepository: CompaniesRepository) =
        FetchCompaniesListUseCase(companiesRepository)

    @Provides
    fun provideFetchCompanyDetailsUseCase(companiesRepository: CompaniesRepository) =
        FetchCompanyDetailsUseCase(companiesRepository)
}
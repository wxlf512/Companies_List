package dev.wxlf.companieslist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.wxlf.companieslist.data.CompaniesRepositoryImpl
import dev.wxlf.companieslist.data.datasources.CompaniesRemoteDataSource
import dev.wxlf.companieslist.data.datasources.remote.RetrofitCompaniesDataSource
import dev.wxlf.companieslist.data.network.CompaniesApi
import dev.wxlf.companieslist.domain.CompaniesRepository

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideRemoteDataSource(companiesApi: CompaniesApi): CompaniesRemoteDataSource =
        RetrofitCompaniesDataSource(companiesApi)

    @Provides
    fun provideSpaceXRepository(remote: CompaniesRemoteDataSource): CompaniesRepository =
        CompaniesRepositoryImpl(remote)
}
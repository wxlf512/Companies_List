package dev.wxlf.companieslist.data.network

import dev.wxlf.companieslist.data.entities.CompanyDetailsEntity
import dev.wxlf.companieslist.data.entities.CompanyEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface CompaniesApi {

    @GET("./test.php")
    suspend fun loadCompaniesList(): List<CompanyEntity>

    @GET("./test.php")
    suspend fun loadDetailsById(@Query("id") id: String): List<CompanyDetailsEntity>
}
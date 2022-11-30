package dev.wxlf.companieslist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.wxlf.companieslist.data.network.CompaniesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideGsonConverter(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideSpaceXApi(gson: GsonConverterFactory,okHttpClient: OkHttpClient): CompaniesApi =
        Retrofit.Builder()
            .baseUrl("https://lifehack.studio/test_task/")
            .client(okHttpClient)
            .addConverterFactory(gson)
            .build()
            .create(CompaniesApi::class.java)
}
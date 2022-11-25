package com.yusufyildiz.ecommerceappnew.di

import com.yusufyildiz.ecommerceappnew.common.Constants.BASE_URL
import com.yusufyildiz.ecommerceappnew.data.source.remote.country.CountryAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCountryService(retrofit: Retrofit): CountryAPI =
        retrofit.create(CountryAPI::class.java)

}
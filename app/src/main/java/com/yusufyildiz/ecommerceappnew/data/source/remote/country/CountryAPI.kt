package com.yusufyildiz.ecommerceappnew.data.source.remote.country

import com.yusufyildiz.ecommerceappnew.data.model.remote.cities.CityData
import com.yusufyildiz.ecommerceappnew.data.model.remote.countries.Country
import com.yusufyildiz.ecommerceappnew.data.model.remote.countries.CountryData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface CountryAPI {

    @POST("/api/v0.1/countries/states")
    suspend fun postCountryName(@Body country: Country) : CityData

    @GET("api/v0.1/countries")
    suspend fun getAllCountries() : CountryData
}


package com.yusufyildiz.ecommerceappnew.data.model.remote.countries

import com.google.gson.annotations.SerializedName

data class CountryDetail(
    @SerializedName("iso2")
    var iso2 : String?,
    @SerializedName("iso3")
    var iso3 : String?,
    @SerializedName("country")
    var countryName : String?,
    @SerializedName("cities")
    var cities : List<String>?
)
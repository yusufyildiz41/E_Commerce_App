package com.yusufyildiz.ecommerceappnew.data.model.remote.countries

import com.google.gson.annotations.SerializedName

data class CountryData(
    @SerializedName("error")
    var errorFeedback : Boolean?,
    @SerializedName("msg")
    var message : String?,
    @SerializedName("data")
    var countryList : List<CountryDetail>?
)
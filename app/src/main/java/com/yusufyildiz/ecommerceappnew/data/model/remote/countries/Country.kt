package com.yusufyildiz.ecommerceappnew.data.model.remote.countries

import com.google.gson.annotations.SerializedName


data class Country(
    @SerializedName("country")
    val countryName : String
)
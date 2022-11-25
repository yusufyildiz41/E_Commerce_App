package com.yusufyildiz.ecommerceappnew.data.model.remote.cities

import com.google.gson.annotations.SerializedName

data class CityDetail (
    @SerializedName("name")
    var name : String?,
    @SerializedName("iso3")
    var iso3 : String?,
    @SerializedName("iso2")
    var iso2 : String?,
    @SerializedName("states")
    var states : ArrayList<States>?
)
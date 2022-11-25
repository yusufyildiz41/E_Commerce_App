package com.yusufyildiz.ecommerceappnew.data.model.remote.cities

import com.google.gson.annotations.SerializedName

data class CityData(
    @SerializedName("error")
    var error : Boolean?,
    @SerializedName("msg")
    var msg : String?,
    @SerializedName("data")
    var data : CityDetail?
)
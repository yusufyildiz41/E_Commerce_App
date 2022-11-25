package com.yusufyildiz.ecommerceappnew.data.model.remote.cities

import com.google.gson.annotations.SerializedName

data class States(
    @SerializedName("name")
    var name : String?,
    @SerializedName("state_code")
    var stateCode : String?
)
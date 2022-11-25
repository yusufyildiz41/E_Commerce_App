package com.yusufyildiz.ecommerceappnew.data.model.remote.user

data class User(
    var userId: String,
    val email: String,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val country: String,
    val city: String,
    val shortAddress: String
)
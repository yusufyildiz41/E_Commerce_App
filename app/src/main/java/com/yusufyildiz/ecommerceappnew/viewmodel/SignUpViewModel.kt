package com.yusufyildiz.ecommerceappnew.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.yusufyildiz.ecommerceappnew.R
import com.yusufyildiz.ecommerceappnew.common.AuthOperations
import com.yusufyildiz.ecommerceappnew.common.showToast
import com.yusufyildiz.ecommerceappnew.data.model.remote.countries.Country
import com.yusufyildiz.ecommerceappnew.data.model.remote.user.User
import com.yusufyildiz.ecommerceappnew.data.source.remote.country.CountryAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val countryApi: CountryAPI, private val authOperations: AuthOperations
) : ViewModel() {

    val countriesLiveData = MutableLiveData<List<String>>()
    val citiesLiveData = MutableLiveData<List<String>>()
    val signUpResult = MutableLiveData<String>()

    init {
        viewModelScope.launch {

            val countries = arrayListOf<String>()
            countryApi.getAllCountries().countryList?.forEach {
                it.countryName?.let { country ->
                    countries.add(country)
                }
            }

            countriesLiveData.value = countries
        }

    }


    fun cities(country: Country)
    {
        viewModelScope.launch {
            val cities = arrayListOf<String>()
            countryApi.postCountryName(country).data?.let { cityDetail ->
                cityDetail.states?.let { states ->
                    states.forEach {
                        cities.add(it.name.toString())
                    }
                }
            }

            citiesLiveData.value = cities
        }
    }

    fun signUp(email: String, password: String)
    {
        viewModelScope.launch {
            authOperations.signUpWithEmailAndPassword(email, password,
                onSuccess = {
                    signUpResult.value = it
                }, onFailure = {
                    signUpResult.value= it
                })
        }
    }

    fun saveUser(user: User) {
        viewModelScope.launch {
            authOperations.saveUser(user)
        }
    }

}
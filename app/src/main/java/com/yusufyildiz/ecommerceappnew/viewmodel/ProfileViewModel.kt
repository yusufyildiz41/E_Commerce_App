package com.yusufyildiz.ecommerceappnew.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufyildiz.ecommerceappnew.common.AuthOperations
import com.yusufyildiz.ecommerceappnew.data.model.remote.user.User
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authOperations: AuthOperations
) : ViewModel() {

    val userInfoLiveData = MutableLiveData<User?>()
    fun getUserData(email: String)
    {
        viewModelScope.launch {
            userInfoLiveData.value = authOperations.getUserData(email).value
        }
    }

}
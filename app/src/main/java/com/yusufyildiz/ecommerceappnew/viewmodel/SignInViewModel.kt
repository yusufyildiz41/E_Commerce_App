package com.yusufyildiz.ecommerceappnew.viewmodel

import android.app.Activity
import android.content.IntentSender
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.SignInClient
import com.yusufyildiz.ecommerceappnew.common.AuthOperations
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val authOperations: AuthOperations
) : ViewModel() {

    var signInResult = MutableLiveData<String>()
    var signInGoogleResult = MutableLiveData<String>()

    fun signIn(email: String, password: String)
    {
        viewModelScope.launch {
            authOperations.signInWithEmailAndPassword(email, password,
                onSuccess = {
                    signInResult.value = it
                },
                onFailure = {
                    signInResult.value = it
                }
            )
        }
    }

    fun signInGoogle(activity : Activity,oneTapClient : SignInClient)
    {
        viewModelScope.launch {
            authOperations.signInWithGoogle(activity,oneTapClient,
            onSuccess = {

            }, onFailure = {
                signInResult.value = it
                })
        }

    }


}
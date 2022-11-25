package com.yusufyildiz.ecommerceappnew.common

import android.app.Activity
import android.content.IntentSender
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.OAuthProvider
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.yusufyildiz.ecommerceappnew.R
import com.yusufyildiz.ecommerceappnew.data.model.remote.user.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthOperations @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
     private val firebaseFirestore: FirebaseFirestore
     )
{

    fun signUpWithEmailAndPassword(
        email : String,
        password : String,
        onSuccess : (String) -> Unit = {},
        onFailure : (String) -> Unit = {}
    )
    {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener { authResult ->
                authResult.user?.let {
                    onSuccess("Successful")
                }
            }.addOnFailureListener {
                onFailure(it.message.orEmpty())
            }

    }

    fun saveUser(user: User)
    {
        val document = firebaseFirestore.collection("users").document(user.email)
        user.userId = document.id
        val userInfo = document.set(user)
        userInfo.addOnSuccessListener {
                Log.d("Firebase","Successful")
        }
        userInfo.addOnFailureListener {
                Log.d("Firebase","Failed")
        }
    }

    fun getUserData(userEmail: String): MutableLiveData<User?>
    {

        //val userData= MutableLiveData<User?>()
//        var userRef = firebaseFirestore.collection("users").document(userEmail)
//
//        userRef.get()
//            .addOnSuccessListener { document ->
//                if(document != null)
//                {
//                    println("data : ${document.data}")
//                    val user = document.toObject(User::class.java)
//                    println("user email: ${user?.email}")
////                    if (user != null) {
////                        userData.value = user
////                    }
//                }
//                else
//                {
//                    println("data is not found")
//                }
//
//            }.addOnFailureListener {
//                println("failed")
//            }
        return MutableLiveData()

    }

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onSuccess: (String) -> Unit = {},
        onFailure: (String) -> Unit = {}
    )
    {
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener { authResult ->
                authResult.user?.let {
                    onSuccess("Successful")
                }
            }.addOnFailureListener{
                onFailure(it.message.orEmpty())
            }
    }

    fun checkCurrentUser(currentUser: (FirebaseUser) -> Unit = {}){
        firebaseAuth.currentUser?.let{
            currentUser(it)
        }
    }
    fun getCurrentUser(): FirebaseUser?
    {
        return firebaseAuth.currentUser
    }

    fun signInWithGoogle(
        activity : Activity,
        oneTapClient : SignInClient,
        onSuccess: (IntentSenderRequest) -> Unit = {},
        onFailure: (String) -> Unit = {}
        )
    {
        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(activity.getString(R.string.default_web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()

        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(activity) { result ->
                try {
                    val intentSenderRequest =
                        IntentSenderRequest.Builder(result.pendingIntent.intentSender)
                            .build()
                    onSuccess(intentSenderRequest)


                }catch (e:IntentSender.SendIntentException){
                    onFailure("Couldn't start One Tap UI : ${e.message}")
                }
            }
            .addOnFailureListener {
                onFailure(it.message.orEmpty())
            }

    }

    fun signInWithTwitter(
        activity: Activity,
        onSuccess: () -> Unit ={},
        onFailure: (String) -> Unit ={}
    )
    {
        val provider = OAuthProvider.newBuilder("twitter.com")
        provider.addCustomParameter("lang","")

        firebaseAuth.startActivityForSignInWithProvider(activity,provider.build())
            .addOnSuccessListener { authResult->
                authResult.user?.let {
                    onSuccess()
                }
            }
            .addOnFailureListener {
                onFailure(it.message.orEmpty())
            }

    }

    fun signInWithGithub(
        activity: Activity,
        onSuccess: () -> Unit = {},
        onFailure: (String) -> Unit ={}
    )
    {
        val provider = OAuthProvider.newBuilder("github.com")
        provider.addCustomParameter("login","")

        firebaseAuth.startActivityForSignInWithProvider(activity,provider.build())
            .addOnSuccessListener { authResult ->
                authResult.user?.let {
                    onSuccess()
                }
            }
            .addOnFailureListener {
                onFailure(it.message.orEmpty())
            }
    }


    fun signOut()
    {
        firebaseAuth.signOut()
    }






}
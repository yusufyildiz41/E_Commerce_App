package com.yusufyildiz.ecommerceappnew.di


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yusufyildiz.ecommerceappnew.common.AuthOperations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun provideFirebaseUser() = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore


    @Singleton
    @Provides
    fun provideAuthOperationsWrapper(firebaseAuth : FirebaseAuth,firebaseFirestore: FirebaseFirestore) =
        AuthOperations(firebaseAuth,firebaseFirestore)
}
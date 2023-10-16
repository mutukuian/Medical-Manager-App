package com.medicalmanager.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun providesFirebaseAuthentication() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesFirebaseFireStore() = FirebaseFirestore.getInstance()




}
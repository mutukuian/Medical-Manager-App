package com.medicalmanager.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.medicalmanager.data.repository.AppointmentRepositoryImpl
import com.medicalmanager.data.repository.AuthRepositoryImpl
import com.medicalmanager.data.repository.DoctorRepositoryImpl
import com.medicalmanager.data.repository.SearchControllerRepositoryImpl
import com.medicalmanager.domain.repository.AppointmentRepository
import com.medicalmanager.domain.repository.AuthRepository
import com.medicalmanager.domain.repository.DoctorRepository
import com.medicalmanager.domain.repository.SearchControllerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providesAuthenticationRepository(firebaseAuth: FirebaseAuth):AuthRepository{
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesDoctorRepository(firebaseFireStore: FirebaseFirestore):DoctorRepository{
        return DoctorRepositoryImpl(firebaseFireStore)
    }

    @Provides
    @Singleton
    fun providesAppointmentRepository(firebaseAuth: FirebaseAuth,fireStore: FirebaseFirestore):AppointmentRepository{
        return AppointmentRepositoryImpl(fireStore, firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesSearchControllerRepository(firebaseAuth: FirebaseAuth,fireStore: FirebaseFirestore):SearchControllerRepository{
        return SearchControllerRepositoryImpl(firebaseAuth, fireStore)
    }
}
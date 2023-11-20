package com.medicalmanager.domain.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.medicalmanager.core.common.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun loginUser(email:String,password:String):Flow<Resource<AuthResult>>

    fun registerUser(email: String,password: String):Flow<Resource<AuthResult>>

    fun googleSignIng(credential:AuthCredential):Flow<Resource<AuthResult>>
}
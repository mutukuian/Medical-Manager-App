package com.medicalmanager.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.medicalmanager.core.common.Resource
import com.medicalmanager.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
):AuthRepository {
    override suspend fun getUserDetails(email: String): Flow<Resource<String>> {
        return flow {
            emit(Resource.Loading())
            val result =firebaseAuth.currentUser
            val userEmail = result?.email?:"N/A"
            emit(Resource.Success(userEmail))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }

    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
      return flow {
          emit(Resource.Loading())
          val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
          emit(Resource.Success(result))
      }.catch {
          emit(Resource.Error(it.message.toString()))
      }
    }

    override fun registerUser(email: String, password: String): Flow<Resource<AuthResult>> {
       return flow {
           emit(Resource.Loading())
           val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
           emit(Resource.Success(result))
       }.catch {
           emit(Resource.Error(it.message.toString()))
       }
    }


    override fun googleSignIng(credential: AuthCredential): Flow<Resource<AuthResult>> {
        return  flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithCredential(credential).await()
            emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }


    override suspend fun signOut(): Flow<Resource<Unit>> {
      return flow {
          emit(Resource.Loading())
          val result =firebaseAuth.signOut()
          emit(Resource.Success(result))
      }.catch {
          emit(Resource.Error(it.message.toString()))
      }
    }
}
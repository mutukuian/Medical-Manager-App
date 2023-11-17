package com.medicalmanager.presentation.authentication.google_auth

import android.content.Context
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GoogleAuthClientUi @Inject constructor(
    private val context: Context,
    private val oneTapClient: SignInClient,
    private val firebaseAuth: FirebaseAuth
) {

    suspend fun signIn():IntentSender?{
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        }catch (e:Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return  result?.pendingIntent?.intentSender
    }

    private fun buildSignInRequest():BeginSignInRequest{
        return  BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId()
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }


















}













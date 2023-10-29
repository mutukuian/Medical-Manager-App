package com.medicalmanager.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.medicalmanager.domain.model.SearchControllerModel
import com.medicalmanager.domain.repository.SearchControllerRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SearchControllerRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
):SearchControllerRepository {

    private val doctorsCollection = fireStore.collection("Doctors")
    override suspend fun searchDoctor(query: String): List<SearchControllerModel> {
        val searchResult = mutableListOf<SearchControllerModel>()
       doctorsCollection.get().addOnSuccessListener { querySnapshot ->
           for (document in querySnapshot.documents){
               val response =SearchControllerModel(
                   name = document.get("Name").toString()
               )
               searchResult.add(response)
               Log.d("TAG" ,"${searchResult}")
           }
       }.await()
        return searchResult
    }
}

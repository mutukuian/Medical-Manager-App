package com.medicalmanager.presentation.searchcontroller.search_screen

import com.medicalmanager.domain.model.SearchControllerModel

data class SearchState(
    val isLoading:Boolean = false,
    val data: List<SearchControllerModel>? = null,
    val error:String? = null
)

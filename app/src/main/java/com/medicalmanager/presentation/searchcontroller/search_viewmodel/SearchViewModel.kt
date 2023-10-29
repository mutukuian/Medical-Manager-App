package com.medicalmanager.presentation.searchcontroller.search_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medicalmanager.domain.use_case.SearchControllerUseCase
import com.medicalmanager.presentation.searchcontroller.search_screen.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchControllerUseCase:SearchControllerUseCase
):ViewModel() {
    private val _searchResults:MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val searchResults:StateFlow<SearchState> = _searchResults


    fun fetchSearchDoctor(query:String) = viewModelScope.launch {
       val results = getSearchControllerUseCase(query)

        if (results != null){
            _searchResults.value = SearchState(isLoading = false,data = results)
            return@launch
        }
        else{
            _searchResults.value = SearchState(error = "Not Found.")

        }
    }
}
package com.medicalmanager.presentation.searchcontroller.search_viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medicalmanager.core.common.Constants.PARAM_QUERY
import com.medicalmanager.core.common.Resource
import com.medicalmanager.domain.use_case.SearchControllerUseCase
import com.medicalmanager.presentation.searchcontroller.search_screen.SearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchControllerUseCase:SearchControllerUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel() {
    private val _searchResults:MutableStateFlow<SearchState> = MutableStateFlow(SearchState())
    val searchResults:StateFlow<SearchState> = _searchResults


    init {
        savedStateHandle.get<String>(PARAM_QUERY)?.let {
            query ->
            fetchSearchDoctor(query)
        }
    }

    fun fetchSearchDoctor(query:String) {
        getSearchControllerUseCase(query).onEach {
            result ->
            when(result){
                is Resource.Success ->{
                    _searchResults.value = SearchState(data = result.data)
                }
                is Resource.Error ->{
                    _searchResults.value = SearchState(error = result.message?: "Results Not Found")
                }
                is Resource.Loading ->{
                    _searchResults.value = SearchState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}

















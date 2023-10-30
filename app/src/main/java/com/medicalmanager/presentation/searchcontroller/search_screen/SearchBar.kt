package com.medicalmanager.presentation.searchcontroller.search_screen




import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.medicalmanager.R
import com.medicalmanager.presentation.searchcontroller.search_viewmodel.SearchViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarM3(
    viewModel:SearchViewModel = hiltViewModel()
){


    val searchState by viewModel.searchResults.collectAsState()

    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }


    SearchBar(
        query =query,
        onQueryChange ={query = it},
        onSearch ={newQuery->
            println("Performing Search on query: $newQuery")
            viewModel.fetchSearchDoctor(newQuery)
        },
        active =active,
        onActiveChange ={active = it},
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        },
        trailingIcon ={
            Row {

                IconButton(onClick = { /* open mic dialog */ }) {
                    Icon(painter = painterResource(R.drawable.mic), contentDescription = "microphone")
                }
                if (active) {
                    IconButton(
                        onClick = { if (query.isNotEmpty()) query = "" else active = false }
                    ) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                    }
                }
            }
        }

    ) {

        if (searchState.error?.isNotEmpty() == true){
            searchState.error?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)

                )
            }

        }
        if (searchState.isLoading){
            CircularProgressIndicator()
        }
    }

}























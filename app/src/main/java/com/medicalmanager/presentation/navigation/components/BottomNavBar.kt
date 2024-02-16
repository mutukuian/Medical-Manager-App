package com.medicalmanager.presentation.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(
    items:List<NavBarItemHolder>,
    navController: NavController,
    itemClick:(NavBarItemHolder) ->Unit
){
    val  backStackEntry by navController.currentBackStackEntryAsState()

    Card(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
       // elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            items.forEach { item ->
                val selected = item.route == backStackEntry?.destination?.route
                NavBarItem(
                    item = item,
                    onClick = {itemClick(item)},
                    selected = selected
                )
            }
        }
    }
}
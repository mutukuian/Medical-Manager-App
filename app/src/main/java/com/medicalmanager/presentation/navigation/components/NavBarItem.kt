package com.medicalmanager.presentation.navigation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NavBarItem(item:NavBarItemHolder,onClick:() -> Unit,selected:Boolean){
    val backgroundColor = if(selected) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f) else Color.Transparent
    val contentColor = if(selected) MaterialTheme.colorScheme.onSurface else Color.Gray

    Box(modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .background(backgroundColor)
        .clickable { onClick() }
    ){
        Row(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                imageVector = item.icon,
                tint = contentColor,
                contentDescription = item.title
            )
            Spacer(modifier = Modifier.padding(horizontal = 0.dp))
            AnimatedVisibility(visible = selected) {
                Text(
                    text = item.title,
                    textAlign = TextAlign.Center,
                    color = contentColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }


}
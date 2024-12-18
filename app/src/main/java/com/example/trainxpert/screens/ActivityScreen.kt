package com.example.trainxpert.screens

import LocalActivityViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.components.ActivitySection
import com.example.trainxpert.model.ActivityItem
import com.example.trainxpert.ui.theme.ButtonColor
import com.example.trainxpert.ui.theme.ButtonTextSize
import com.example.trainxpert.ui.theme.CardTitle

//import com.example.trainxpert.viewmodels.LocalActivityViewModel

@Composable
fun ActivityScreen(
    modifier: Modifier = Modifier,
    onNavigateToDetails: (ActivityItem) -> Unit,
    onNavigateToAdd: () -> Unit
) {
    val viewModel = LocalActivityViewModel.current
    val activitiesByCategory by viewModel.activitiesByCategory.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadActivities()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = Icons.Filled.Person.name,
                        modifier = Modifier.size(48.dp),
                    )
                    Text(
                        text = " Activité",
                        style = TextStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 22.sp,
                        ),
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }





            items(activitiesByCategory.entries.toList()) { entry ->
                val (category, activities) = entry
                ActivitySection(
                    title = category,
                    activities = activities,
                    onActivityClick = onNavigateToDetails
                )
            }


        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onNavigateToAdd,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)
        ) {
            Text("+ Ajouter une activité", fontSize = ButtonTextSize)
        }
    }
}

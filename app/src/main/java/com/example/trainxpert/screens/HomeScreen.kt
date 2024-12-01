package com.example.trainxpert.screens

import SportCalendarScreen
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DatePicker
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.R
import com.example.trainxpert.components.DailyTipCard
import com.example.trainxpert.viewmodels.LocalSportSessionViewModel
import java.util.Date

@Composable
fun HomeScreen(modifier: Modifier){

//    val viewModel = LocalSportSessionViewModel.current
//    val sessions by viewModel.sessions.collectAsState(initial = emptyList())
//    viewModel.addSession("test_3", Date(2024, 12, 4),10 ,10.0 ,10)
//    viewModel.loadSessions()
//    println("Sessions: $sessions")


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Texte de bienvenue
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.icon_xpert),
                contentDescription = "",
                Modifier.size(108.dp)
            )

            Text(
                text = "Bienvenue sur TrainXpert",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                ),
                modifier = Modifier.align(Alignment.CenterVertically)

            )
        }


        DailyTipCard()

        SportCalendarScreen()
    }

}
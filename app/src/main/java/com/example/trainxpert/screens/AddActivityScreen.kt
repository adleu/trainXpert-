package com.example.trainxpert.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.components.DateTimePickerField
import com.example.trainxpert.model.ActivityItem
import com.example.trainxpert.ui.theme.BottomBarHeight
import com.example.trainxpert.ui.theme.ButtonColor
import com.example.trainxpert.ui.theme.ButtonTextSize
import com.example.trainxpert.ui.theme.CardTitle
import com.example.trainxpert.ui.theme.FormFontSize
import com.example.trainxpert.ui.theme.MainPadding
import com.example.trainxpert.ui.theme.TextFieldFontSizeData
import com.example.trainxpert.ui.theme.formBackground
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddActivityScreen(
    activities: List<ActivityItem>,
    onCancel: () -> Unit,
    onSave: (String, LocalDateTime, Int, Double?, Int?) -> Unit,
    backAction: () -> Unit
) {
    var selectedActivity by remember { mutableStateOf<ActivityItem?>(null) }
    var selectedDateTime by remember { mutableStateOf<LocalDateTime?>(LocalDateTime.now()) }
    var duration by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var dropdownExpanded by remember { mutableStateOf(false) }

    val TextFieldColor = TextFieldDefaults.textFieldColors(
        containerColor = formBackground,
        cursorColor = Color.Black,
        focusedIndicatorColor = Color.Black,
        unfocusedIndicatorColor = Color.Black,
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black
    )

    val TextFieldTextStyle = LocalTextStyle.current.copy(fontSize = TextFieldFontSizeData, color = CardTitle)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ajouter une activité", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Retour"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = selectedActivity?.title ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Activité", fontSize = FormFontSize) },
                    trailingIcon = {
                        IconButton(onClick = { dropdownExpanded = true }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MainPadding),
                    colors = TextFieldColor,
                    textStyle = TextFieldTextStyle

                )

                DropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(formBackground)
                        .align(Alignment.CenterHorizontally)
                ) {
                    activities.forEach { activity ->
                        DropdownMenuItem(
                            text = { Text(activity.title) },
                            onClick = {
                                selectedActivity = activity
                                dropdownExpanded = false
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // DateTime Picker Field
                DateTimePickerField(
                    initialDateTime = LocalDateTime.now(),
                    onDateTimeSelected = { selectedDateTime = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = duration,
                    onValueChange = { duration = it },
                    label = { Text("Durée (min)", fontSize = FormFontSize) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MainPadding),
                    colors = TextFieldColor,
                    textStyle = TextFieldTextStyle
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = distance,
                    onValueChange = { distance = it },
                    label = { Text("Distance (km)", fontSize = FormFontSize) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MainPadding),
                    colors = TextFieldColor,
                    textStyle = TextFieldTextStyle
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = calories,
                    onValueChange = { calories = it },
                    label = { Text("Calories brûlées", fontSize = FormFontSize) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MainPadding),
                    textStyle = TextFieldTextStyle,
                    colors = TextFieldColor,
                    )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(MainPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
//                Button(
//                    onClick = onCancel,
//                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Text("Annuler", color = Color.White)
//                }

                Button(
                    onClick = {
                        if (selectedActivity != null && selectedDateTime != null) {
                            onSave(
                                selectedActivity!!.title,
                                selectedDateTime!!,
                                duration.toIntOrNull() ?: 0,
                                distance.toDoubleOrNull(),
                                calories.toIntOrNull()
                            )
                        }
                    },
                    colors = ButtonDefaults.buttonColors(ButtonColor),
                    modifier = Modifier
                        .padding(bottom = BottomBarHeight)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(0.5f)
                ) {
                    Text(
                        "Enregistrer",
                        color = Color.White,
                        fontSize = ButtonTextSize
                    )
                }
            }
        }
    }
}


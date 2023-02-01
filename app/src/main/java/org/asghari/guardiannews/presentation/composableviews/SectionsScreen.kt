package org.asghari.guardiannews.presentation.composableviews

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import org.asghari.guardiannews.other.SectionsState
import org.asghari.guardiannews.presentation.viewmodels.SectionsViewModel


@Composable
fun SectionsScreen() {
    val sectionsViewModel:SectionsViewModel = hiltViewModel()
    val sections=sectionsViewModel.sectionList.value
    Column() {

    Text(text = "Sections" , modifier = Modifier
        .padding(horizontal = 10.dp, vertical = 15.dp)
        .background(
            Color.White
        ), textAlign = TextAlign.Start, style = TextStyle(color = Color.DarkGray, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    )
    when(sections)
    {
        is SectionsState.Loading -> {
            Text(text = "loading!!!")
        }
        is SectionsState.Error -> {
            Text(text = "Error: ${sections.message}")
        }
        is SectionsState.Success -> {
            var sectionsList = sections.data.response.results
            LazyColumn{
                items(sectionsList){ item ->

                    Text(text = "${item.id}" , modifier = Modifier
                        .padding(horizontal = 7.dp, vertical = 6.dp)
                        .clip(shape = RoundedCornerShape(7.dp))
                        .background(
                            Color.Cyan
                        ).border(1.dp, Color.LightGray, shape = RoundedCornerShape(7.dp)).padding(horizontal = 10.dp, vertical = 15.dp).fillMaxWidth()
                       , textAlign = TextAlign.Start, style = TextStyle(color = Color.DarkGray, fontSize = 14.sp)
                    )

                }
            }
        }

    }


    }
}
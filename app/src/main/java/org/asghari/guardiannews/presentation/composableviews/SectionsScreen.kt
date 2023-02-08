package org.asghari.guardiannews.presentation.composableviews

import FlowRow
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import org.asghari.guardiannews.other.SectionsState
import org.asghari.guardiannews.presentation.ui.RoundedCheckView
import org.asghari.guardiannews.presentation.viewmodels.SectionsViewModel


@Composable
fun SectionsScreen() {
    val sectionsViewModel: SectionsViewModel = hiltViewModel()
    val sections = sectionsViewModel.sectionList.value
    val selectedSections = sectionsViewModel.selectedSectionsList.value
    var currentSelectedSectionsCount = remember {
        mutableStateOf(0)
    }
    Column() {

        Text(
            text = "${currentSelectedSectionsCount.value} section is selected",
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .background(
                    Color.White
                ),
            textAlign = TextAlign.Start,
            style = TextStyle(
                color = Color.DarkGray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        when (sections) {
            is SectionsState.Loading -> {
                Text(text = "loading!!!")
            }
            is SectionsState.Error -> {
                Text(text = "Error: ${sections.message}")
            }
            is SectionsState.Success -> {
                var sectionsList = sections.data.response.results

                Box(modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(weight = 1f, fill = false)) {
                    FlowRow(
                        horizontalGap = 6.dp,
                        verticalGap = 6.dp,

                        alignment = Alignment.Start,
                    ) {
                        repeat(sectionsList.size) { index ->
                            var item = sectionsList.get(index)
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 2.dp, vertical = 2.dp)
                                    .clip(shape = RoundedCornerShape(17.dp))
                                    .background(
                                        Color(0xfffdfdfd)
                                    )
                                    .border(
                                        1.dp,
                                        Color.LightGray,
                                        shape = RoundedCornerShape(17.dp)
                                    )
                                    .padding(horizontal = 3.dp, vertical = 2.dp)

                            )
                            {

                                var sectionState = selectedSections.contains(item.id)
                                LaunchedEffect(selectedSections.size) {
                                    currentSelectedSectionsCount.value = (selectedSections.size-1)
                                }

                                RoundedCheckView("${item.id}" , sectionState , ontoggleSection =  { isChecked, sectionId ->
                                    sectionsViewModel.toggleSelectedSection(isChecked,sectionId)
                                    if(isChecked)
                                        currentSelectedSectionsCount.value++
                                    else
                                        currentSelectedSectionsCount.value--
                            })

                            }
                        }
                    }

                }
            }
        }

    }
}

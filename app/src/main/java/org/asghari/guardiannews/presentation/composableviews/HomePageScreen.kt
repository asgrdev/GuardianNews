package org.asghari.guardiannews.presentation.composableviews

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.unit.dp
import org.asghari.guardiannews.data.models.Result;

@Composable
fun HomePageScreen(newslist: List<Result>) {
    var list =  newslist
    Box(modifier = Modifier
        .background(Color.White)
        .padding(0.dp)
        .fillMaxSize()
        , contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(4.dp)
                .background(Color(0xffffaaaa)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {
            items(list) {
                Text(text = it.webTitle)
                Spacer(modifier = Modifier.height(8.dp).background(color = Color.Blue))
            }
          }
    }
}
 
package org.asghari.guardiannews.presentation.composableviews

import android.text.Spannable
import android.util.Log
import android.widget.ScrollView
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import de.charlex.compose.HtmlText
import org.asghari.guardiannews.other.NewsListState
import org.asghari.guardiannews.presentation.viewmodels.NewsDetailsViewModel
import org.asghari.guardiannews.presentation.viewmodels.NewsListViewModel

@Composable
fun NewsScreen(name: String?,navController: NavController) {

    val _NewsDetailsViewModel: NewsDetailsViewModel = hiltViewModel()
    val dataState: NewsListState = _NewsDetailsViewModel.newsDetails.value
    name?.let {
        _NewsDetailsViewModel.getNewsDetails(newsId = it, "")
    }
    val scrollState = rememberScrollState()

    when (dataState) {
        is NewsListState.Loading -> {
            Text(text = "Loading")
        }
        is NewsListState.Error -> {
            Text(text = "Error>" + dataState.message)
        }
        is NewsListState.Success -> {
            var news = dataState.data.response.results.get(0)

            Column(
                modifier = Modifier.verticalScroll(scrollState )
                /* modifier = Modifier.clickable(onClick = {
                        Log.d(">>>>", news.webTitle)
                    })*/
            ) {
                Text(
                    text = news.webTitle, color = Color.DarkGray,
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 15.dp),
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Start
                )
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = news.fields.thumbnail)
                            .allowHardware(false)
                            .build()
                    ),
                    contentDescription = news.webTitle,
                    modifier = Modifier
                        .padding(all = 5.dp)
                        .background(Color.LightGray)
                        .wrapContentSize()
                        .height(212.dp)
                        .fillMaxSize()
                )

                Text(
                    text = news.webPublicationDate, color = Color.Gray,
                    modifier = Modifier.padding(horizontal = 10.dp),
                    style = MaterialTheme.typography.overline,
                    textAlign = TextAlign.Start
                )
                Spacer(
                    modifier = Modifier
                        .height(15.dp)
                        .background(color = Color.Blue)
                )

                HtmlText(
                    text =  news.fields.body , color = Color.DarkGray,
                    modifier = Modifier.padding(horizontal = 10.dp),
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Start
                )
                Spacer(
                    modifier = Modifier
                        .height(38.dp)
                        .background(color = Color.Blue)
                )
            }
        }

    }
}
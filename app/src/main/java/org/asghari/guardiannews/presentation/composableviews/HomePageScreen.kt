package org.asghari.guardiannews.presentation.composableviews

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlinx.coroutines.flow.merge
import org.asghari.guardiannews.other.NewsListState
import org.asghari.guardiannews.data.models.Result
import org.asghari.guardiannews.presentation.viewmodels.NewsListViewModel

@Composable
fun LazyListState.OnBottomReached(buffer : Int = 0,
                                  loadMore : () -> Unit
){
    val shouldLoadMore = remember {
        derivedStateOf{
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()?:return@derivedStateOf true
            lastVisibleItem.index >= layoutInfo.totalItemsCount-1-buffer
        }
    }
    LaunchedEffect(shouldLoadMore){
        snapshotFlow{shouldLoadMore.value}.collect{
            if(it) loadMore()
        }
    }
}

@Composable
fun HomePageScreen(onNavigation:(newsId:String?) -> Unit) {

    val _newsListViewModel: NewsListViewModel = hiltViewModel()
    val dataState: NewsListState = _newsListViewModel.newsList.value

         Box(modifier = Modifier
             .background(Color.White)
             .padding(0.dp)
             .fillMaxSize()
            , contentAlignment = Alignment.Center
        ) {
            when (dataState) {
                is NewsListState.Loading -> {
                    Log.d("Loading", "---")
                }
                is NewsListState.Success -> {
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(0.dp)
                            .fillMaxSize(), contentAlignment = Alignment.TopCenter
                    ) {
                        val list:MutableList<org.asghari.guardiannews.data.models.Result> = remember {mutableListOf<org.asghari.guardiannews.data.models.Result>() }
                        dataState?.data?.response?.results?.let { it2 ->
                            list.addAll(it2)
                             val listState = rememberLazyListState()
                            listState.OnBottomReached(buffer = 2) {
                                // do on load more
                                _newsListViewModel.LoadMore()
                             }
                            LazyColumn(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .fillMaxWidth()
                                    .padding(4.dp)
                                    .background(Color(0xfffafafa)),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.End,
                                state = listState
                            ) {
                                items(list) {
                                    Column(
                                        modifier = Modifier.clickable(onClick = {
                                            Log.d(">>>>", it.webTitle)
                                            onNavigation(it.id)
                                        })
                                    ) {
                                        Image(
                                            painter = rememberAsyncImagePainter(
                                                ImageRequest.Builder(LocalContext.current)
                                                    .data(data = it.fields.thumbnail)
                                                    .allowHardware(false)
                                                    .build()
                                            ),
                                            contentDescription = it.webTitle,
                                            modifier = Modifier
                                                .background(Color.LightGray)
                                                .wrapContentSize()
                                                .height(212.dp)
                                                .fillMaxSize()
                                        )
                                        Text(
                                            text = it.webTitle, color = Color.Gray,
                                            modifier = Modifier.padding(horizontal = 10.dp),
                                            style = MaterialTheme.typography.body2,
                                            textAlign = TextAlign.Start
                                        )
                                        Text(
                                            text = it.webPublicationDate, color = Color.Gray,
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
                    }
                }
                is NewsListState.Error -> {
                    Log.d("Error", "---")
                }
            }
        }
    }


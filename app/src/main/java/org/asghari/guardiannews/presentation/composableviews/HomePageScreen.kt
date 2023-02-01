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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.asghari.guardiannews.other.NewsListState
import org.asghari.guardiannews.other.LoadMoreLoading
import org.asghari.guardiannews.presentation.viewmodels.NewsListViewModel

@Composable
fun LazyListState.OnBottomReached(buffer : Int = 0,
                                  loadMore : () -> Unit
){
    val shouldLoadMore = remember {
        derivedStateOf{

    val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true

     lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - buffer &&  layoutInfo.totalItemsCount>5

        }
    }
    LaunchedEffect(shouldLoadMore){
        snapshotFlow{shouldLoadMore.value}.collect{
            if(it) loadMore()
        }
    }
}

@Composable
fun HomePageScreen(state: MutableState<TextFieldValue>,onNavigation:(newsId:String?) -> Unit) {

    val _newsListViewModel: NewsListViewModel = hiltViewModel()
    var dataState: NewsListState = _newsListViewModel.newsList.value
    val swipeRefreshState = rememberSwipeRefreshState(false)
    Log.d("Loading", dataState.javaClass.name)
    LaunchedEffect(state.value.text){
        Log.d("ggg>",state.value.text)
        if(!_newsListViewModel.currentSearchQuery.equals(state.value.text)){
        if(state.value.text.length>=3) {
                _newsListViewModel.getNewsList(state.value.text)
        }
        _newsListViewModel.tmpNewsList?.let {
        if(state.value.text.length<=0 &&  it.response.results.size>0)
        {
            _newsListViewModel.getNewsList(state.value.text)
        }
        }
        }
    }
    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(0.dp)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        val listState = rememberLazyListState()
        listState.OnBottomReached(buffer = 1) {
            // do on load more
            Log.d(
                "Loading3",
                listState.firstVisibleItemIndex.toString() + ">>>" + dataState.javaClass.name
            )

            _newsListViewModel.LoadMore(state.value.text)
        }

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                _newsListViewModel.getNewsList(state.value.text)
                swipeRefreshState.isRefreshing = true
            },
        )
        {

            LazyColumn(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(4.dp)
                    .background(Color(0xfffafafa)),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End,
                state = listState
            ) {

                when (dataState) {
                    is NewsListState.Loading -> {

                        dataState?.data?.response?.results?.let { list ->
                            items(list) {

                                Column(
                                    modifier = Modifier.clickable(onClick = {

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
                        if (swipeRefreshState.isRefreshing == false) {
                            item {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    LoadMoreLoading()
                                }

                            }
                        }

                    }
                    is NewsListState.Success -> {
                        swipeRefreshState.isRefreshing = false
                        dataState?.data?.response?.results?.let { list ->

                            items(list) {
                                Column(
                                    modifier = Modifier.clickable(onClick = {

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
                    is NewsListState.Error -> {
                        Log.d("Error", "---")
                    }
                }
            }
        }
    }
}


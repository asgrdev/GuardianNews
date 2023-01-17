package org.asghari.guardiannews.presentation.composableviews

import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.stopScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.os.HandlerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import org.asghari.guardiannews.other.NewsListState
import org.asghari.guardiannews.data.models.Result
import org.asghari.guardiannews.other.LoadMoreLoading
import org.asghari.guardiannews.other.Loading
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
fun HomePageScreen(onNavigation:(newsId:String?) -> Unit) {

    val _newsListViewModel: NewsListViewModel = hiltViewModel()
    var dataState: NewsListState =   _newsListViewModel.newsList.value

    Log.d("Loading", dataState.javaClass.name)
         Box(modifier = Modifier
             .background(Color.White)
             .padding(0.dp)
             .fillMaxSize()
            , contentAlignment = Alignment.Center
        ) {
             val listState = rememberLazyListState()
             listState.OnBottomReached(buffer = 1) {
                 // do on load more
                 Log.d(
                     "Loading3",
                     listState.firstVisibleItemIndex.toString() + ">>>" + dataState.javaClass.name
                 )

                 _newsListViewModel.LoadMore()
             }
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

                         item { LoadMoreLoading() }

                     }
                     is NewsListState.Success -> {

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


package org.asghari.guardiannews.presentation.composableviews

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import org.asghari.guardiannews.other.NewsListState
import org.asghari.guardiannews.other.LoadMoreLoading
import org.asghari.guardiannews.presentation.ui.RoundedCheckView
import org.asghari.guardiannews.presentation.viewmodels.NewsListViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun LazyListState.OnBottomReached(buffer : Int = 0,
                                  loadMore : () -> Unit
){
    val shouldLoadMore = remember {
        derivedStateOf{

    val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true

            ((lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - buffer) &&  layoutInfo.totalItemsCount>5 && lastVisibleItem.index>8)

        }
    }
    LaunchedEffect(shouldLoadMore){
        snapshotFlow { shouldLoadMore.value }.collect {
                if (it) loadMore()
        }
    }
}

@Composable

fun HomePageScreen(state: MutableState<TextFieldValue>,onNavigation:(newsId:String?) -> Unit) {

    val _newsListViewModel: NewsListViewModel = hiltViewModel()
    var dataState: NewsListState = _newsListViewModel.newsList.value
    val swipeRefreshState = rememberSwipeRefreshState(false)
    var notFoundData = remember { mutableStateOf(false) }
    var selectedSectionslist:List<String> = _newsListViewModel.selectedSectionsList.value
    var sectionsToShow = _newsListViewModel.sectionsToShow
    val encodedSearchText = URLEncoder.encode(state.value.text, StandardCharsets.UTF_8.toString())
    LaunchedEffect(state.value.text){
        var sectionsString = sectionsToShow.value.toString().replace("[","").replace("]","")
        if(!_newsListViewModel.currentSearchQuery.equals(encodedSearchText)){
            if(encodedSearchText.length>=3) {
                _newsListViewModel.getNewsList(encodedSearchText,sectionsString)
            }
            _newsListViewModel.tmpNewsList?.let {
                if(encodedSearchText.length<=2 &&  it.response.results.size>0)
                {   notFoundData.value = false
                    _newsListViewModel.getNewsList(encodedSearchText,sectionsString)
                }
            }
        }
    }


    val sectionslistState = rememberLazyListState()

    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(0.dp)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        val listState = rememberLazyListState()
        listState.OnBottomReached(buffer = 1) {
            // do on load more

            if (!notFoundData.value) {
                var sectionsString = sectionsToShow.value.toString().replace("[","").replace("]","")
                 _newsListViewModel.LoadMore(encodedSearchText,sectionsString)
            }
        }
Column(modifier = Modifier.align(Alignment.TopStart)) {

    Box(
        modifier = Modifier
            .background(Color.White)
            .padding(0.dp)
            .fillMaxWidth()
    ) {
        LazyRow(
            state = sectionslistState,
            modifier = Modifier.align(Alignment.TopStart)
                .height(48.dp)
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(6.dp, 6.dp, 6.dp, 1.dp)
                .background(Color(0xfffdfdfd)),
        )
        {
            items(selectedSectionslist) { section ->
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
                        var isSelected =  false
                        if(sectionsToShow.value.indexOf(section.trim())>-1){
                            isSelected =true
                        }

                        RoundedCheckView(section, isSelected, ontoggleSection = { isChecked, sectionId ->
                            if(!isChecked)
                            {
                                _newsListViewModel.addSectionToShow(
                                    !isChecked,
                                    section,
                                    encodedSearchText
                                )
                                return@RoundedCheckView true
                            }
                            else if(sectionsToShow.value.size<=1 && isChecked) {
                                return@RoundedCheckView false
                            }
                            else{
                                _newsListViewModel.addSectionToShow(
                                    !isChecked,
                                    section,
                                    encodedSearchText
                                )
                                return@RoundedCheckView true
                            }

                        })
                }
            }

        }
    }


    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            var sectionsString = sectionsToShow.value.toString().replace("[","").replace("]","")

            _newsListViewModel.getNewsList(encodedSearchText,sectionsString)
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
                    notFoundData.value = (dataState.data.response.total <= 0)
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
}



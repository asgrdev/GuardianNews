package org.asghari.guardiannews.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.asghari.guardiannews.presentation.NewsListState
import org.asghari.guardiannews.presentation.composableviews.HomePageScreen
import org.asghari.guardiannews.presentation.ui.theme.GuardianNewsTheme
import org.asghari.guardiannews.presentation.viewmodels.NewsListViewModel

@AndroidEntryPoint
class MainActivity2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val _newsListViewModel: NewsListViewModel = hiltViewModel()

            GuardianNewsTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val dataState: NewsListState? =  _newsListViewModel.newsList.value
                    dataState?.data?.response?.results?.let {
                        HomePageScreen(it)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GuardianNewsTheme {
        HomePageScreen(emptyList())
    }
}
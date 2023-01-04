package org.asghari.guardiannews.presentation.composableviews

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NewsScreen(name: String) {
    Text(text = "Hello $name!")
}
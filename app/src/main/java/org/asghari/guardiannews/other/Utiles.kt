package org.asghari.guardiannews.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import org.asghari.guardiannews.R
@Preview
@Composable
fun Loading() {
    val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec
        .RawRes(R.raw.root_load))
    val lottieAnimationState by animateLottieCompositionAsState(composition = lottieComposition, iterations = LottieConstants.IterateForever)

    Column(
        modifier = Modifier
            .size(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LottieAnimation(lottieComposition, alignment = Alignment.Center)
    }
}

@Composable
fun LoadMoreLoading() {
    val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec
        .RawRes(R.raw.root_load))
    val lottieAnimationState by animateLottieCompositionAsState(composition = lottieComposition, iterations = LottieConstants.IterateForever)

    Column(
        modifier = Modifier
            .size(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LottieAnimation(lottieComposition, alignment = Alignment.Center)
    }
}
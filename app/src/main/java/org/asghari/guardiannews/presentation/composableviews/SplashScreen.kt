package org.asghari.guardiannews.presentation.composableviews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import org.asghari.guardiannews.other.ScreensRoute.HomePage

@Composable
fun SplashScreen(navController: NavController) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()

    )
    {
        Column(modifier = Modifier.align(Alignment.Center)) {

            Text(
                "The Guardian",
                style = TextStyle(
                    color = Color.Blue,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
            )
            LaunchedEffect(true) {
                delay(2000L)
                navController.popBackStack()
                navController.navigate(HomePage().route)
            }
        }
    }
}
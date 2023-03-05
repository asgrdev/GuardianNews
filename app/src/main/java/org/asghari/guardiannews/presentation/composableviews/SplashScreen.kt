package org.asghari.guardiannews.presentation.composableviews

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import org.asghari.guardiannews.other.ScreensRoute.HomePage

@Composable
fun SplashScreen(navController: NavController) {
    var isVisible by remember { mutableStateOf(false) }
    var timeToShowNews by remember { mutableStateOf(false) }
    val trasition = updateTransition(targetState = isVisible, label = null)
    var newsplaceString  by remember { mutableStateOf("         ")}
    val color by trasition.animateColor(
        transitionSpec = { tween(3000) },
        label = "changeColor",
        targetValueByState = {isVisible ->
            if(!isVisible) Color(173, 236, 243, 240) else  Color(3, 140, 202, 255)
        }
    )
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
    {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Row {
                AnimatedVisibility(visible = isVisible, enter = fadeIn(animationSpec = tween(2000)))
                {
                    Text(
                        "The Guardian",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            color = color,
                            fontSize = 31.sp,
                            textAlign = TextAlign.Center
                        )
                    )
                }

                Text(
                    newsplaceString,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color(2, 60, 87, 255),
                        fontSize = 31.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }
            LaunchedEffect(true) {
                delay(50L)
                isVisible = true
                delay(2800L)
                timeToShowNews = true
                newsplaceString = " News"
                delay(600L)
                navController.popBackStack()
                navController.navigate(HomePage().route)
            }
        }
    }
}
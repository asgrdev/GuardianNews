package org.asghari.guardiannews.presentation.composableviews

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.asghari.guardiannews.other.ScreensRoute
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),

) {
    NavHost(
        navController = navController,
        startDestination =  ScreensRoute.HomePage().route
    ) {
        composable(ScreensRoute.HomePage().route) {
          HomePageScreen {
              val encodedUrl = URLEncoder.encode(it, StandardCharsets.UTF_8.toString())
              navController.navigate( "${ScreensRoute.NewsScreen().route}/$encodedUrl")
          }
        }

        composable("${ScreensRoute.NewsScreen().route}/{newsId}",
        arguments = listOf(navArgument(name = "newsId"){})
        )
        {
            NewsScreen(name = it.arguments?.getString("newsId"),navController)
        }

    }
}

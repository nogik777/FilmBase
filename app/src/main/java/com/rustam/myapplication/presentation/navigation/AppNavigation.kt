package com.rustam.myapplication.presentation.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rustam.myapplication.presentation.detail_screen.DetailScreen
import com.rustam.myapplication.presentation.search_screen.SearchScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.SearchScreen.name
    ) {
        composable(route = Screen.SearchScreen.name) {
            SearchScreen(onItemClick = { title ->
                navController.navigate(
                    route = "${Screen.DetailScreen.name}/$title"
                )
            })
        }
        composable(route = "${Screen.DetailScreen.name}/{title}",
            arguments = listOf(
                navArgument("title"){
                    type = NavType.StringType
                }
            ),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = {-it},
                    animationSpec = tween(
                        300,
                        easing = FastOutSlowInEasing
                    )
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = {-it},
                    animationSpec = tween(
                        300,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        ) {navBackStackEntry ->
            navBackStackEntry.arguments?.getString("title").let {title ->
                DetailScreen(title = title!!)
            }
        }
    }
}
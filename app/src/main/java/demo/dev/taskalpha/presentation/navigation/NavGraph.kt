package demo.dev.taskalpha.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import demo.dev.taskalpha.presentation.splash.SplashScreen
import demo.dev.taskalpha.presentation.taskcreate.TaskCreateScreen
import demo.dev.taskalpha.presentation.tasklist.TaskListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.TaskList.route) {
            TaskListScreen(navController = navController)
        }

        composable(route = Screen.TaskCreate.route) {
            TaskCreateScreen(navController = navController)
        }
    }
}
package demo.dev.taskalpha.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen(ConstantAppScreenName.SPLASH_SCREEN)
    object TaskList : Screen(ConstantAppScreenName.TASK_LIST_SCREEN)
    object TaskCreate : Screen(ConstantAppScreenName.TASK_CREATE_SCREEN)

}

object ConstantAppScreenName {
    const val SPLASH_SCREEN = "splash_screen"
    const val TASK_LIST_SCREEN = "task_list_screen"
    const val TASK_CREATE_SCREEN = "task_create_screen"
}

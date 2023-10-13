package demo.dev.taskalpha.presentation.taskcreate

data class TaskTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)

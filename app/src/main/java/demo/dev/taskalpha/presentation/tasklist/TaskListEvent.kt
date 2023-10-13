package demo.dev.taskalpha.presentation.tasklist

sealed class TaskListEvent {
    data class ToggleTaskStatus(val id: String) : TaskListEvent()
}

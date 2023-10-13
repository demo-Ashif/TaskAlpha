package demo.dev.taskalpha.presentation.tasklist

import demo.dev.taskalpha.domain.model.Task

data class TaskListState(
    val tasks: List<Task> = emptyList(),
)

package demo.dev.taskalpha.data.remote.dto


data class TaskListDto(
    val taskListDto: List<TaskDto>,
    val status: String,
    val totalResults: Int
)

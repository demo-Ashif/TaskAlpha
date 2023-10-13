package demo.dev.taskalpha.data.remote.dto

import demo.dev.taskalpha.core.utils.Helper
import demo.dev.taskalpha.data.local.entities.TasksEntity

data class TaskDto(

    val title: String,
    val description: String?,
    val updatedAt: String
) {
    fun toTaskEntity(): TasksEntity {

        return TasksEntity(
            id = generateUniqueId(),
            taskTitle = title,
            taskDescription = description ?: "Read more ...",
            taskUpdatedAt = Helper.toReadableDate(updatedAt)
        )
    }

    fun generateUniqueId(): String {
        return "$title-$updatedAt"
    }

}

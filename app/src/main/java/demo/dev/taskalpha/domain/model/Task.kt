package demo.dev.taskalpha.domain.model

import demo.dev.taskalpha.data.local.entities.TasksEntity

data class Task(
    val id: String,
    val taskTitle: String,
    val taskStatus: Boolean,
    val taskDescription: String,
    val taskUpdatedAt: Long,
) {
    fun toTaskEntity(): TasksEntity {

        return TasksEntity(
            id = id,
            taskTitle = taskTitle,
            taskStatus = taskStatus,
            taskDescription = taskDescription,
            taskUpdatedAt = taskUpdatedAt
        )
    }
}

class InvalidTaskException(message: String) : Exception(message)

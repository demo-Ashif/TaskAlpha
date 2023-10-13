package demo.dev.taskalpha.domain.model

import demo.dev.taskalpha.data.local.entities.TasksEntity
import demo.dev.taskalpha.data.remote.dto.TaskDto

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

    fun toTaskDto(): TaskDto {

        return TaskDto(
            id = id,
            title = taskTitle,
            taskStatus = taskStatus,
            description = taskDescription,
            updatedAt = taskUpdatedAt
        )
    }
}

class InvalidTaskException(message: String) : Exception(message)

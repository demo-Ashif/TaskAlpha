package demo.dev.taskalpha.data.remote.dto

import demo.dev.taskalpha.core.utils.Helper
import demo.dev.taskalpha.data.local.entities.TasksEntity

data class TaskDto(
val id:String,
    val title: String,
    val taskStatus: Int=0,
    val description: String?,
    val updatedAt: Long
) {
    fun toTaskEntity(): TasksEntity {

        return TasksEntity(
            id = id,
            taskTitle = title,
            taskStatus=taskStatus,
            taskDescription = description ?: "Read more ...",
            taskUpdatedAt = updatedAt
        )
    }



}

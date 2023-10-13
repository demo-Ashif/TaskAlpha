package demo.dev.taskalpha.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import demo.dev.taskalpha.core.app.Constants
import demo.dev.taskalpha.domain.model.Task

@Entity(tableName = Constants.DB_TABLE_TASKS)
data class TasksEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val taskTitle: String,
    val taskDescription: String,
    val taskUpdatedAt: String,
) {
    fun toTask(): Task {
        return Task(
            id = id,
            taskTitle = taskTitle,
            taskDescription = taskDescription,
            taskUpdatedAt = taskUpdatedAt
        )
    }
}
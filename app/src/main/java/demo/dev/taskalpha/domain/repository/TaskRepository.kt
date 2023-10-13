package demo.dev.taskalpha.domain.repository

import demo.dev.taskalpha.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAllTask(): Flow<List<Task>>
    suspend fun getTaskDetail(id: String): Task?
    suspend fun updateTaskById(id: String)
    suspend fun createNewTask(task: Task)

    suspend fun triggerTaskSync()
}
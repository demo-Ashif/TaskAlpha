package demo.dev.taskalpha.data.repository

import demo.dev.taskalpha.data.local.TaskDB
import demo.dev.taskalpha.data.remote.api.TaskApi
import demo.dev.taskalpha.domain.model.Task
import demo.dev.taskalpha.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val taskApi: TaskApi,
    private val taskDB: TaskDB
) : TaskRepository {

    private val taskDao = taskDB.taskDao()
    override fun getAllTask(): Flow<List<Task>> {
        TODO("Not yet implemented")
    }

    override fun updateTaskDetail(id: String): Flow<Task> {
        TODO("Not yet implemented")
    }

}
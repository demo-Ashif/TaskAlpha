package demo.dev.taskalpha.data.repository

import demo.dev.taskalpha.data.local.TaskDB
import demo.dev.taskalpha.data.remote.api.TaskApi
import demo.dev.taskalpha.domain.model.Task
import demo.dev.taskalpha.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskApi: TaskApi,
    private val taskDB: TaskDB
) : TaskRepository {

    private val dao = taskDB.taskDao()
    override fun getAllTask(): Flow<List<Task>> {
        return dao.getAllTasks().map { tasksEntities -> tasksEntities.map { it.toTask() } }
    }

    override suspend fun getTaskDetail(id: String): Task {
        return dao.getTaskDetail(id).toTask()
    }

    override suspend fun updateTaskById(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun createNewTask(task: Task) {
        dao.createTask(task.toTaskEntity())
    }


}
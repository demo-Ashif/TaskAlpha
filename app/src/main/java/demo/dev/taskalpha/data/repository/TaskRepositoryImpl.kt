package demo.dev.taskalpha.data.repository

import demo.dev.taskalpha.data.local.TaskDB
import demo.dev.taskalpha.data.remote.api.TaskApi
import demo.dev.taskalpha.data.worker.SyncManager
import demo.dev.taskalpha.domain.model.Task
import demo.dev.taskalpha.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskApi: TaskApi,
    private val taskDB: TaskDB,
    private val syncManager: SyncManager
) : TaskRepository {

    private val dao = taskDB.taskDao()
    override fun getAllTask(): Flow<List<Task>> {
        return dao.getAllTasks().map { tasksEntities -> tasksEntities.map { it.toTask() } }

    }

    override suspend fun getTaskDetail(id: String): Task {
        return dao.getTaskDetail(id).toTask()
    }

    override suspend fun updateTaskById(id: String) {
        dao.updateTaskById(id)

        // Dummy call to the server for demo purposes

//        try {
//
//            val response = taskApi.updateTask(id)
//            if (response.isSuccessful) {
//                Result.success(Unit)
//            } else {
//                Result.failure(Exception("API call failed"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
    }

    override suspend fun createNewTask(task: Task) {
        dao.createTask(task.toTaskEntity())

        // Dummy call to the server for demo purposes
//        try {
//
//            val response = taskApi.createTask(task.toTaskDto())
//            if (response.isSuccessful) {
//                Result.success(Unit)
//            } else {
//                Result.failure(Exception("API call failed"))
//            }
//        } catch (e: Exception) {
//            Result.failure(e)
//        }
    }

    override suspend fun triggerTaskSync() {
        syncManager.syncData()
    }


}
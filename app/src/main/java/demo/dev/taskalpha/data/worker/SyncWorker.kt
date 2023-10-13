package demo.dev.taskalpha.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import demo.dev.taskalpha.domain.model.Task
import demo.dev.taskalpha.domain.repository.TaskRepository

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val taskRepository: TaskRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val tasksList = mutableListOf<Task>()
            taskRepository.getAllTask().collect { tasksList.addAll(it) }

            tasksList.forEach { task ->
                val apiResult = taskRepository.createNewTask(task)
                //here we can can response behaviour and do as per that
            }

            Result.success()
        } catch (e: Exception) {
            return Result.failure()
        }
    }


}

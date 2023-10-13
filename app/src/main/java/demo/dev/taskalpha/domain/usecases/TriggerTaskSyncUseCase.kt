package demo.dev.taskalpha.domain.usecases

import demo.dev.taskalpha.domain.model.Task
import demo.dev.taskalpha.domain.repository.TaskRepository

class TriggerTaskSyncUseCase(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task: Task) {
        return taskRepository.triggerTaskSync()
    }
}
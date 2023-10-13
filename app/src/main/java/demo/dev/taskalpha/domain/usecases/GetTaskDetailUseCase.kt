package demo.dev.taskalpha.domain.usecases

import demo.dev.taskalpha.domain.model.Task
import demo.dev.taskalpha.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTaskDetailUseCase(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(id: String): Task? {
        return taskRepository.getTaskDetail(id)
    }
}
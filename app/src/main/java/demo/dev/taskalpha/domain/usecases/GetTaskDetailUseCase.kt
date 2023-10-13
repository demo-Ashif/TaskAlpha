package demo.dev.taskalpha.domain.usecases

import demo.dev.taskalpha.domain.model.Task
import demo.dev.taskalpha.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTaskDetailUseCase(private val taskRepository: TaskRepository) {
    operator fun invoke(id: String): Flow<Task> {
        return taskRepository.updateTaskDetail(id)
    }
}
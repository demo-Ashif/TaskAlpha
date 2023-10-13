package demo.dev.taskalpha.domain.usecases

import demo.dev.taskalpha.domain.repository.TaskRepository

class UpdateTaskStatusUseCase(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(id: String) {
        return taskRepository.updateTaskById(id)
    }
}
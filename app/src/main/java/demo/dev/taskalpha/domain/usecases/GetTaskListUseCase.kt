package demo.dev.taskalpha.domain.usecases

import demo.dev.taskalpha.domain.repository.TaskRepository

class GetTaskListUseCase(private val taskRepository: TaskRepository) {
    operator fun invoke() = taskRepository.getAllTask()
}
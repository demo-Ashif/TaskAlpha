package demo.dev.taskalpha.domain.usecases

data class TaskUseCase(
    val getTaskListUseCase: GetTaskListUseCase,
    val getTaskDetailUseCase: GetTaskDetailUseCase,
    val createNewTaskUseCase: CreateNewTaskUseCase
)

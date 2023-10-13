package demo.dev.taskalpha.presentation.tasklist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import demo.dev.taskalpha.domain.usecases.TaskUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val taskUseCase: TaskUseCase
) : ViewModel() {
    private val _state = mutableStateOf(TaskListState())
    val state: State<TaskListState> = _state


    private var getAllTasksJob: Job? = null

    init {
        getTasks()
    }

    fun onEvent(event: TaskListEvent) {
        when (event) {
            is TaskListEvent.ToggleTaskStatus -> {
                viewModelScope.launch {
                    try {
                        taskUseCase.updateTaskStatusUseCase(event.id)
                    } catch (e: Exception) {

                    }
                }
                getTasks()
            }
        }
    }

    private fun getTasks() {
        getAllTasksJob?.cancel()
        getAllTasksJob = taskUseCase.getTaskListUseCase()
            .onEach { tasks ->
                _state.value = state.value.copy(
                    tasks = tasks,
                )
            }
            .launchIn(viewModelScope)
    }
}